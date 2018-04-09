package ee.ituk.memberlist.server.security.verification;

import ee.ituk.memberlist.server.config.MagicLinkConfig;
import ee.ituk.memberlist.server.email.EmailService;
import net.sargue.mailgun.Response;
import org.apache.velocity.VelocityContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
@EnableScheduling
public class VerificationService {

    public static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Resource
    private MagicLinkConfig magicLinkConfig;

    @Resource
    private EmailService emailService;

    private Map<String, LoginVerification> store = new HashMap<>();

    public Optional<LoginVerification> createIfNotExist(String email) {
        if (store.containsKey(email) && !isExpired(store.get(email))) {
            return Optional.empty();
        } else {
            LoginVerification loginVerification = new LoginVerification(UUID.randomUUID().toString());
            store.put(email, loginVerification);
            return Optional.of(loginVerification);
        }
    }

    public Optional<LoginVerification> get(String email) {
        return Optional.ofNullable(store.remove(email));
    }

    public boolean isExpired(LoginVerification loginVerification) {
        return Duration.between(loginVerification.getCreatedAt(), LocalDateTime.now()).toMinutes() > magicLinkConfig.getTimeToLive();
    }

    public String buildMagicLink(String to, LoginVerification loginVerification, String referrer) {
        return UriComponentsBuilder.fromUriString(referrer)
                .replacePath("/")
                .fragment("/login/" + to + "/" + loginVerification.getKey())
                .toUriString();
    }

    public CompletableFuture<Response> sendMagicLink(String to, LoginVerification loginVerification, String referrer) {
        VelocityContext context = createContext();
        context.put("loginLink", buildMagicLink(to, loginVerification, referrer));
        return emailService.sendAsync(to, "login", context, "Magic Link | MemberList | ITÜK");
    }


    private VelocityContext createContext() {
        VelocityContext context = new VelocityContext();
        context.put("datePattern", DATE_PATTERN);
        return context;
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void scheduledStoreCleaner() {
        store.keySet().forEach(key -> {
            if (isExpired(store.get(key))) {
                store.remove(key);
            }
        });
    }
}
