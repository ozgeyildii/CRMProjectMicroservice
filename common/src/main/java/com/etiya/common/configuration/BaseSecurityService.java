package com.etiya.common.configuration;

import com.etiya.common.filters.JwtAuthFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
public class BaseSecurityService {

    private final JwtAuthFilter jwtAuthFilter;

    public BaseSecurityService(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/auth/**"
    };
    public HttpSecurity configureCoreSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URLS).permitAll())

                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("""
            {
                "status": 401,
                "error": "Unauthorized",
                "message": "Kimlik doğrulaması yapılmamış veya token eksik."
            }
        """);
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("""
            {
                "status": 403,
                "error": "Forbidden",
                "message": "Bu işlemi yapmaya yetkiniz yok."
            }
        """);
                        })
                )

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity;
    }
}