package com.idus.work.core.config.security;

import com.idus.work.member.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                .httpBasic().disable().csrf().disable()
//                .httpBasic().and()
                .authorizeHttpRequests()
//                .antMatchers("/member").hasRole("USER")
                .antMatchers("/", "/**").permitAll()
//                .antMatchers("/", "/**").hasRole("USER")
                .anyRequest().authenticated().and().userDetailsService(customUserDetailService);
        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/css/**", "/swagger-ui/**");
//    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
