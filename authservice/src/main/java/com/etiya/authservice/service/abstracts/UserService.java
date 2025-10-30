package com.etiya.authservice.service.abstracts;

import com.etiya.authservice.service.dtos.requests.RegisterUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void add(RegisterUserRequest request);
}
