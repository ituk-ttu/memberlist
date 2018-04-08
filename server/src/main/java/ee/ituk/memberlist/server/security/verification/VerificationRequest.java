package ee.ituk.memberlist.server.security.verification;

import lombok.Data;

@Data
public class VerificationRequest {
    private String email;
    private String key;
}
