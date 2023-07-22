package com.kujproject.kuj.web.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationFailureHandler authFailureHandler;

//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring().mvcMatchers(
//                "/v3/api-docs/**",
//                "/swagger-ui/**",
//                "/api/v1/login" // 임시
//        );
//    }

    //https://samori.tistory.com/64
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                //.requestMatchers("/loginform", "/signinform", "/signin", "/").permitAll()
                .anyRequest().permitAll())
                .formLogin((login) -> login
                        .loginPage("/loginform")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureHandler(authFailureHandler)
                        .permitAll())
                .csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }
}
