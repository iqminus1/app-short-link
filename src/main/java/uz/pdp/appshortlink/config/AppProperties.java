package uz.pdp.appshortlink.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private JWT jwt;

    @Getter
    @Setter
    public static class JWT {
        private String secretKey;
        private Integer expireDays;
    }
}
