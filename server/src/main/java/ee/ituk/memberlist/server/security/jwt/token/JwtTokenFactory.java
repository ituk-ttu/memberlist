package ee.ituk.memberlist.server.security.jwt.token;

import ee.ituk.memberlist.server.config.JwtConfig;
import ee.ituk.memberlist.server.security.jwt.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtTokenFactory {
    private final JwtConfig config;

    @Autowired
    public JwtTokenFactory(JwtConfig config) {
        this.config = config;
    }

    public AccessToken createAccessToken(UserContext userContext) {

        Claims claims = Jwts.claims().setSubject(userContext.getId().toString());
        claims.put("scopes", userContext.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()));

        LocalDateTime currentTime = LocalDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(config.getIssuer())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(config.getTtl())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, config.getKey())
                .compact();

        return new AccessToken(token, claims);
    }

    public JwtToken createRefreshToken(UserContext userContext) {

        LocalDateTime currentTime = LocalDateTime.now();

        Claims claims = Jwts.claims().setSubject(userContext.getId().toString());
        claims.put("refresh", true);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(config.getIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(config.getRefreshable())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, config.getKey())
                .compact();

        return new AccessToken(token, claims);
    }
}