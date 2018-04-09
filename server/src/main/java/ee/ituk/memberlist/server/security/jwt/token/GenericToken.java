package ee.ituk.memberlist.server.security.jwt.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class GenericToken implements JwtToken {

    @Getter
    private String token;

}
