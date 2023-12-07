package noteasy.sundo.global.library.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.global.configuration.FilterConfiguration;
import noteasy.sundo.global.library.security.handler.CustomAccessDeniedHandler;
import noteasy.sundo.global.library.security.handler.CustomAuthenticationEntryPointHandler;
import noteasy.sundo.global.library.security.token.JwtTokenParser;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenParser jwtTokenParser;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors()
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest)
                .permitAll()

                .mvcMatchers(HttpMethod.GET, "/").permitAll()

                // auth
                .mvcMatchers(HttpMethod.POST, "/auth/student").permitAll()
                .mvcMatchers(HttpMethod.POST, "/auth/teacher").permitAll()

                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPointHandler(new ObjectMapper()))
                .accessDeniedHandler(new CustomAccessDeniedHandler(new ObjectMapper()))
                .and()
                .apply(new FilterConfiguration(jwtTokenParser))
                .and()
                .build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
