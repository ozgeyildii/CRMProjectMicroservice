package com.etiya.authservice.service.abstracts;

import com.etiya.authservice.service.dtos.requests.LoginRequest;
import com.etiya.authservice.service.dtos.requests.RegisterUserRequest;
import com.etiya.authservice.service.dtos.responses.LoginResponse;

public interface AuthService {
    void register(RegisterUserRequest request);
    LoginResponse login(LoginRequest request);
}
