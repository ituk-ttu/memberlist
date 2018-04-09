package ee.ituk.memberlist.server.security.jwt.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class RefreshToken implements JwtToken {
    @Getter
    private String token;
    @Getter
    private Jws<Claims> claims;

    private RefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }

    public static Optional<RefreshToken> create(GenericToken token, String signingKey) {
        Jws<Claims> claims = token.parseClaims(signingKey);

        Boolean isRefreshToken = claims.getBody().get("refresh", Boolean.class);
        if (isRefreshToken != null && isRefreshToken) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(claims));
    }

    public String getJti() {
        return claims.getBody().getId();
    }

    public String getSubject() {
        return claims.getBody().getSubject();
    }
}