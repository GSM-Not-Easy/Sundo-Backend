package noteasy.sundo.global.library.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.http.HttpServletRequest;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) {
//        http.corzs()
//                .and()
//                .csrf().disable()
//
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//
//                // health
//                .mvcMatchers(HttpMethod.GET, "/").permitAll()
//                .anyRequest().authenticated()
//
////                .exceptionHandling()
////                .authenticationEntryPoint(CustomAuthenticationEntryPointHandler())
////                .accessDeniedHandler(CustomAccessDeniedHandler())
////                .and()
////
////                .apply(new FilterConfig(jwtTokenProvider))
////                .and()
//                .build();
        return null;
    }
}
