package noteasy.sundo.global.library.security.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    static class Properties {
        private static final String tokenPrefix = "Bearer ";
        private static final String tokenHeader = "Authorization";
        private static final String accessType = "access";
        private static final String refreshType = "refresh";
        private static final String roleType = "roleType";
    }

    private final String accessSecret;
    private final String refreshSecret;
    private final Integer accessExp;
    private final Integer refreshExp;
}
