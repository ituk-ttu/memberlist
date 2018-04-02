package ee.ituk.memberlist.server.auth.verification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Verification {
    private LocalDateTime createdAt;
    private String key;

    public Verification() {
        createdAt = LocalDateTime.now();
        key = UUID.randomUUID().toString();
    }
}
