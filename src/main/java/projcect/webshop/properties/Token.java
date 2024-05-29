package projcect.webshop.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "token")
public class Token {

    @Value("${token.secret}")
    private String secret;

    @Value("${token.time-to-live}")
    private Long timeToLive;

}
