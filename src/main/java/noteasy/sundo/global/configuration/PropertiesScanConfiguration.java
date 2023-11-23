package noteasy.sundo.global.configuration;

import noteasy.sundo.global.library.security.token.JwtProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = JwtProperties.class)
public class PropertiesScanConfiguration {
}
