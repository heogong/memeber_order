package com.idus.work.core.config.security;

import com.idus.work.member.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
//@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailService;

    // TODO 참고 : https://velog.io/@csh0034/Spring-Security-Config-Refactoring
    @Bean
    @Order(0)
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
        return http.requestMatchers(matchers -> matchers
                .antMatchers("/images/**", "/js/**", "/css/**", "/swagger-ui/**")).build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/view/member/login").permitAll()
                .antMatchers("/view/member/create").permitAll()
                .antMatchers("/api/v1/member").permitAll()
                .antMatchers("/v2/api-docs/**").permitAll()
                .antMatchers("/swagger*/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/").hasRole("USER")
                .and()
                    .headers()
                    .frameOptions().sameOrigin()
                .and()
                .formLogin()
                    .loginPage("/view/member/login")
                    .loginProcessingUrl("/loginProc")
                    .usernameParameter("id")
                    .passwordParameter("pw")
                    .defaultSuccessUrl("/view/member/list", true)
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .userDetailsService(customUserDetailService)
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
