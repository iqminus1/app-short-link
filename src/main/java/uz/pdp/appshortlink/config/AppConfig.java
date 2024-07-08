package uz.pdp.appshortlink.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Random;

@OpenAPIDefinition(
        info = @Info(
                title = "App api description",
                version = "1v",
                contact = @Contact(
                        name = "unnamed", email = "wwxeww1wwxeww@gmail.com", url = "https://github.com/iqminus"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://springdoc.org"),
                termsOfService = "http://swagger.io/terms/",
                description = "no description"
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring 6 Wiki Documentation", url = "https://springshop.wiki.github.org/docs"
        ),
        servers = {
                @Server(
                        url = "http://localhost:80",
                        description = "Production-Server"
                ),
                @Server(
                        url = "http://localhost:8080",
                        description = "Test-Server"
                )
        }
)
@Configuration
public class AppConfig {
    @Bean
    public Random random() {
        return new Random();
    }

//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("url", "user");
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*");
            }
        };
    }
}
