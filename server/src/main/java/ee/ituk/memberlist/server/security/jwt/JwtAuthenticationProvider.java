package ee.ituk.memberlist.server.security.jwt;

import ee.ituk.memberlist.server.config.JwtConfig;
import ee.ituk.memberlist.server.member.MemberStatus;
import ee.ituk.memberlist.server.security.jwt.token.AuthenticationToken;
import ee.ituk.memberlist.server.security.jwt.token.GenericToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Resource
    private JwtConfig jwtConfig;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        GenericToken token = (GenericToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = token.parseClaims(jwtConfig.getKey());
        Long id = Long.valueOf(jwsClaims.getBody().getSubject());
        String name = jwsClaims.getBody().get("name", String.class);
        String memberStatus = jwsClaims.getBody().get("status", String.class);
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(memberStatus));

        UserContext context = new UserContext(id, name, authorities);

        return new AuthenticationToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (AuthenticationToken.class.isAssignableFrom(authentication));
    }
}