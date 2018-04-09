package ee.ituk.memberlist.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.magiclink")
@Data
public class MagicLinkConfig {
    private Long timeToLive;
}
