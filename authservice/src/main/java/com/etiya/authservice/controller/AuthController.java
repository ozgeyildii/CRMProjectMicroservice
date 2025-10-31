package com.etiya.authservice.controller;

import com.etiya.authservice.service.abstracts.AuthService;
import com.etiya.authservice.service.dtos.requests.LoginRequest;
import com.etiya.authservice.service.dtos.requests.RegisterUserRequest;
import com.etiya.authservice.service.dtos.responses.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterUserRequest request){
        authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody @Valid LoginRequest request){
        return authService.login(request);
    }
}
