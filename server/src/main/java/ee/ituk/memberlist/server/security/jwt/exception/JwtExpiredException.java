package ee.ituk.memberlist.server.security.jwt.exception;

import ee.ituk.memberlist.server.security.jwt.token.JwtToken;
import org.springframework.security.core.AuthenticationException;

public class JwtExpiredException extends AuthenticationException {

    private JwtToken token;

    public JwtExpiredException(String msg) {
        super(msg);
    }

    public JwtExpiredException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}