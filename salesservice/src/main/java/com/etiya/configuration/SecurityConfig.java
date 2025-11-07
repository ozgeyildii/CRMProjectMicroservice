package com.etiya.salesservice.configuration;

import com.etiya.common.configuration.BaseSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final BaseSecurityService baseSecurityService;

    public SecurityConfig(BaseSecurityService baseSecurityService) {
        this.baseSecurityService = baseSecurityService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        baseSecurityService.configureCoreSecurity(httpSecurity);
        httpSecurity.authorizeHttpRequests(req->req.anyRequest().authenticated());
        return httpSecurity.build();
    }
}