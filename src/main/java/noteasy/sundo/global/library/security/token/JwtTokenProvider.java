package noteasy.sundo.global.library.security.token;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private static final String tokenPrefix = "Bearer ";
    private static final String tokenHeader = "Authorization";
    private static final String accessType = "access";
    private static final String refreshType = "refresh";
    private static final String roleType = "roleType";

    

}