package ee.ituk.memberlist.server.auth.verification;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class VerificationService {
    private static final int MINUTES_TO_LIVE = 15;
    private Map<String, Verification> verificationMap = new HashMap<>();

    public Verification create(String email) throws VerificationAlreadyExistsException {
        if (verificationMap.containsKey(email) && isVerificationExpired(verificationMap.get(email))) {
            throw new VerificationAlreadyExistsException();
        } else {
            Verification verification = new Verification();
            verificationMap.put(email, verification);
            return verification;
        }
    }

    public boolean verify(String email, String key) {
        return verificationMap.containsKey(email) &&
                verificationMap.get(email).getKey().equals(key) &&
                !isVerificationExpired(verificationMap.get(email));
    }

    private boolean isVerificationExpired(Verification verification) {
        return ChronoUnit.MINUTES.between(verification.getCreatedAt(), LocalDateTime.now()) > 15;
    }
}
