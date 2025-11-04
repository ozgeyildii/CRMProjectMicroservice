package com.etiya.common.configuration;

import com.etiya.common.jwt.JwtService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private final JwtService jwtService;

    public FeignClientInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                requestTemplate.header("Authorization", authHeader);

                // (Opsiyonel) Log veya kontrol amaçlı decode
                String token = authHeader.substring(7);
                String username = jwtService.extractUsername(token);
                System.out.println("[Feign Interceptor] Forwarding token for user: " + username);
            }
        }
    }
}
