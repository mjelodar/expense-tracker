package com.snapp.expense_tracker.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final Environment env;

    public SecurityConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            web.ignoring().requestMatchers(HttpMethod.OPTIONS, "/**");

            if (!env.acceptsProfiles(Profiles.of("prod"))) {
                web.ignoring()
                        .requestMatchers("/swagger-ui/index.html")
                        .requestMatchers("/swagger-ui.html")
                        .requestMatchers("/swagger-ui/**")
                        .requestMatchers("/v3/api-docs/swagger-config")
                        .requestMatchers("/v3/api-docs")
                        .requestMatchers("/swagger-resources/**");
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/signup").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
