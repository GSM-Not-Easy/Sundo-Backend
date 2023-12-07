package noteasy.sundo.global.library.filter;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.global.library.security.SecurityConfig;
import noteasy.sundo.global.library.security.token.JwtTokenParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenParser jwtTokenParser;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String accessToken = jwtTokenParser.parseAccessToken(request);

        if(accessToken != null && !accessToken.isBlank()) {
            Authentication authentication = jwtTokenParser.authentication(accessToken);
            SecurityContextHolder.clearContext();
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
