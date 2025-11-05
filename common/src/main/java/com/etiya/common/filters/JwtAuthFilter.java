package com.etiya.common.filters;

import com.etiya.common.crosscuttingconcerns.exceptions.types.AuthenticationException;
import com.etiya.common.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

            String jwtHeader = request.getHeader("Authorization");

            if (jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
                String jwt = jwtHeader.substring(7);
                String username = jwtService.extractUsername(jwt);
                List<String> roles = jwtService.extractRoles(jwt);
                List<SimpleGrantedAuthority> authorities =
                        roles.stream().map(SimpleGrantedAuthority::new).toList();

                if (jwtService.validateToken(jwt, username)) {
                    UsernamePasswordAuthenticationToken token =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);
                } else {
                    throw new AuthenticationException("Invalid or expired JWT token.");
                }
            }

            filterChain.doFilter(request, response);

        }
    }

