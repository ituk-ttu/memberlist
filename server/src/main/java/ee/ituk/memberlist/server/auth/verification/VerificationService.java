package ee.ituk.memberlist.server.auth.verification;

import ee.ituk.memberlist.server.email.EmailService;
import net.sargue.mailgun.Response;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class VerificationService {
    private static final int MINUTES_TO_LIVE = 15;
    private Map<String, Verification> verificationMap = new HashMap<>();

    @Autowired
    private EmailService emailService;

    public Verification create(String email) throws VerificationAlreadyExistsException {
        if (verificationMap.containsKey(email) && !isVerificationExpired(verificationMap.get(email))) {
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

    private String getMagicLink(Verification verification, String email, String referrer) {
        return UriComponentsBuilder.fromUriString(referrer)
                .replacePath("/")
                .fragment("/auth/" + email + "/" + verification.getKey())
                .toUriString();
    }

    private CompletableFuture<Response> sendMagicLinkEmail(String magicLink, String toEmail) {
        VelocityContext context = emailService.createContext();
        context.put("loginLink", magicLink);
        return emailService.sendAsync(toEmail, "login", context, "Maagiline link | MemberList | ITÜK");
    }

    public CompletableFuture<Response> sendMagicLink(String toEmail, Verification verification, String referrer) {
        return sendMagicLinkEmail(getMagicLink(verification, toEmail, referrer), toEmail);
    }
}
