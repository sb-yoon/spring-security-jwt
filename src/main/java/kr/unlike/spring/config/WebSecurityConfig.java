package kr.unlike.spring.config;

import kr.unlike.spring.auth.JwtAccessDeniedHandler;
import kr.unlike.spring.auth.JwtAuthenticationEntryPoint;
import kr.unlike.spring.auth.JwtTokenFilterConfigurer;
import kr.unlike.spring.auth.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;
    private final JwtTokenProvider tokenProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/resources/**", "/api-docs", "/api-docs/**", "/robots.txt");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();

        http.cors(); // cors 활성화

        http.csrf().disable(); // csrf 사용 x

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // JWT 토큰사용하므로 세션 관리 x

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http.authorizeRequests() // 인증절차 진행
                .antMatchers("/", "/api/v1/auth/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin().disable();

        http.apply(new JwtTokenFilterConfigurer(tokenProvider));
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        // 특정회원은 모두 허용하기 위한 설정
        // 기업은 일반 회원의 모든 권한이 허용된다.
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("CORP > USER");
        return roleHierarchy;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
