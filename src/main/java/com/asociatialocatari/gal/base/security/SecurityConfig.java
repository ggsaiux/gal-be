package com.asociatialocatari.gal.base.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class SecurityConfig {
    @Bean
    public WebSecurityCustomizer websecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/v3/api-docs*/**", "/swagger-ui/**", "/swagger-ui/index.html");
    }
}
