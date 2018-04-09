package ee.ituk.memberlist.server.security.jwt.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import lombok.Getter;


public final class AccessToken implements JwtToken {
    @Getter
    private final String token;
    @JsonIgnore
    @Getter
    private Claims claims;

    protected AccessToken(final String token, Claims claims) {
        this.token = token;
        this.claims = claims;
    }
}