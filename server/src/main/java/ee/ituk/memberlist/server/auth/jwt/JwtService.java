package ee.ituk.memberlist.server.auth.jwt;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import ee.ituk.memberlist.server.member.MemberService;
import ee.ituk.memberlist.server.member.MemberStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultJwtBuilder;
import io.jsonwebtoken.impl.DefaultJwtParser;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@ConfigurationProperties("jwt.signature")
@RequiredArgsConstructor
@AllArgsConstructor
public class JwtService {

    private static final String ISSUER = "IT-teaduskonna üliõpilaskogu";
    private static final Pattern JWT_PATTERN = Pattern.compile("^Bearer (.+)$");
    private static final Duration LIFESPAN = Duration.of(1, ChronoUnit.DAYS);

    @Setter
    private SignatureAlgorithm algorithm;
    @Setter
    private String key;

    @Autowired
    private MemberService memberService;

    public String createFromClaims(JwtClaims claims) {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        return new DefaultJwtBuilder().setClaims(claims)
                .setExpiration(asLegacyDate(now.plus(LIFESPAN)))
                .setIssuedAt(asLegacyDate(now))
                .setIssuer(ISSUER)
                .signWith(algorithm, key)
                .compact();
    }

    public JwtClaims validateAndGetClaims(HttpServletRequest request) {
        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(tokenHeader == null) {
            return null;
        }
        Matcher matcher = JWT_PATTERN.matcher(tokenHeader);
        if(!matcher.find()) {
            return null;
        }
        String token = matcher.group(1);
        return validateAndGetClaims(token);
    }

    public JwtClaims validateAndGetClaims(String token) {
        Jws<Claims> parsedToked = new JwtParser().setSigningKey(key).parseClaimsJws(token);
        JwtClaims claims = new JwtClaims(parsedToked.getBody());
        if(Instant.now().isAfter(claims.getExpiration().toInstant())) {
            return null;
        }
        return claims;
    }

    @SneakyThrows
    public String hashToken(String token) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
        return String.valueOf(Hex.encodeHex(hash));
    }

    public boolean isHashValid(String token, String hash) {
        return hashToken(token).equals(hash);
    }

    private Date asLegacyDate(LocalDateTime exp) {
        return Date.from(exp.toInstant(ZoneOffset.UTC));
    }

    private class JwtParser extends DefaultJwtParser {

        private final ObjectReader objectReader;

        public JwtParser() {
            objectReader = new ObjectMapper().readerFor(Map.class)
                    .withFeatures(DeserializationFeature.USE_LONG_FOR_INTS);
        }

        protected Map<String, Object> readValue(String val) {
            try {
                return objectReader.readValue(val);
            } catch (IOException e) {
                throw new MalformedJwtException("Unable to read JSON value: " + val, e);
            }
        }

    }
}
