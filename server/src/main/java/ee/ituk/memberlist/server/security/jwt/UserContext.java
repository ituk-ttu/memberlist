package ee.ituk.memberlist.server.security.jwt;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
public class UserContext {
    private final Long id;
    private final String name;
    private final List<GrantedAuthority> authorities;

    public UserContext(Long id, String name, List<GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
    }
}