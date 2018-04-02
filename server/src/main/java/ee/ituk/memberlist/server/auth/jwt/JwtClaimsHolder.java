package ee.ituk.memberlist.server.auth.jwt;

import lombok.RequiredArgsConstructor;

// Can't inject JwtClaims directly because of MapMethodProcessor
@RequiredArgsConstructor
public class JwtClaimsHolder {

    private final JwtClaims claims;

    public JwtClaims get() {
        return claims != null ? claims : new JwtClaims();
    }
}
