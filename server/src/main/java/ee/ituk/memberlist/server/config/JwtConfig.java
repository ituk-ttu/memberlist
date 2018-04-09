package ee.ituk.memberlist.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt.config")
@Data
public class JwtConfig {
    private Integer ttl;
    private String issuer;
    private String key;
    private Integer refreshable;
}
