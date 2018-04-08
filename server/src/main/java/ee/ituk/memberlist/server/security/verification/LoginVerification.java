package ee.ituk.memberlist.server.security.verification;

import lombok.Data;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Data
public class LoginVerification {
    private String key;
    private LocalDateTime createdAt;

    public LoginVerification(String key) {
        this.key = key;
        createdAt = LocalDateTime.now();
    }
}
