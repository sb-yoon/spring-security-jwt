package kr.unlike.spring.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter filter = new JwtTokenFilter(tokenProvider);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
