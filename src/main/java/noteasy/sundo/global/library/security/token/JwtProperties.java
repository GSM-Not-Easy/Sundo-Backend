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

    @Getter
    static class Properties {
        public static final String tokenPrefix = "Bearer ";
        public static final String tokenHeader = "Authorization";
        public static final String accessType = "access";
        public static final String refreshType = "refresh";
        public static final String roleType = "roleType";
    }

    private final String accessSecret;
    private final String refreshSecret;
    private final Integer accessExp;
    private final Integer refreshExp;
}
