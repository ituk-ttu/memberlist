package ee.ituk.memberlist.server.security.jwt.token;

import ee.ituk.memberlist.server.security.jwt.exception.JwtExpiredException;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;

public interface JwtToken {
    String getToken();

    default public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.getToken());
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            throw new JwtExpiredException(this, "JWT Token expired", expiredEx);
        }
    }
}
