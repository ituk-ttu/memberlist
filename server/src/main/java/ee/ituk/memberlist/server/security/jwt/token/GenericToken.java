package ee.ituk.memberlist.server.security.jwt.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenericToken implements JwtToken {

    private String token;

}
