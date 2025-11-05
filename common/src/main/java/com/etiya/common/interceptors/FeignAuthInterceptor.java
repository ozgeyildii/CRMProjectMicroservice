package com.etiya.common.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if(attributes!=null){
            HttpServletRequest request = attributes.getRequest();

            String authHeader = request.getHeader("Authorization");

            if(authHeader!=null){
                requestTemplate.header("Authorization",authHeader);
            }
        }

    }
}