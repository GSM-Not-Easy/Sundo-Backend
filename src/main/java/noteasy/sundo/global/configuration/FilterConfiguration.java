package noteasy.sundo.global.configuration;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.global.library.filter.JwtFilter;
import noteasy.sundo.global.library.security.token.JwtTokenParser;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenParser jwtTokenParser;

    @Override
    public void configure(HttpSecurity builder) {
        builder.addFilterBefore(new JwtFilter(jwtTokenParser), UsernamePasswordAuthenticationFilter.class);
    }
}
