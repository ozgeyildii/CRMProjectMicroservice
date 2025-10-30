package com.etiya.authservice.service.abstracts;

import com.etiya.authservice.service.dtos.requests.LoginRequest;
import com.etiya.authservice.service.dtos.requests.RegisterUserRequest;

public interface AuthService {
    void register(RegisterUserRequest request);
    String login(LoginRequest request);
}
