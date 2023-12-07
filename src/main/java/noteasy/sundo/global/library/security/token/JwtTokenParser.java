package noteasy.sundo.global.library.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.global.library.security.principle.AuthDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class JwtTokenParser {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;

    public String parseAccessToken(HttpServletRequest request) {
        String header = request.getHeader(JwtProperties.Properties.tokenHeader); // Authorization

        if(header == null || header.isBlank()) {
            return null;
        }

        if(header.startsWith(JwtProperties.Properties.tokenPrefix)) {
            return header.replace(JwtProperties.Properties.tokenHeader, "");
        }

        return null;
    }

    public String parseRefreshToken(String refreshToken) {
        if(refreshToken.isBlank()) {
            return null;
        }

        if(refreshToken.startsWith(JwtProperties.Properties.tokenPrefix)) {
            return refreshToken.replace(JwtProperties.Properties.tokenPrefix, "");
        }

        return null;
    }

    public Authentication authentication(String accessToken) {
        UserDetails subject = authDetailsService.loadUserByUsername(getTokenBody(accessToken, jwtProperties.getAccessSecret()).getSubject());
        return new UsernamePasswordAuthenticationToken(subject, "", subject.getAuthorities());
    }

    public Boolean isExpiredRefreshToken(String refreshToken){
        try {
            getTokenBody(refreshToken, jwtProperties.getRefreshSecret()).getSubject();
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    private Claims getTokenBody(String token, String secret) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
