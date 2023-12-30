package noteasy.sundo.global.configuration;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("Authorization", "RefreshToken", "Content-Type")
                .allowedOrigins("/**")
                .allowedMethods("GET", "POST", "PATCH","PUT","DELETE","HEAD")
                .maxAge(3000);
    }
}
