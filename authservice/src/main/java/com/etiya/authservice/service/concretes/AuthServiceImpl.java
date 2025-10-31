package com.etiya.authservice.service.concretes;


import com.etiya.authservice.service.abstracts.AuthService;
import com.etiya.authservice.service.abstracts.UserService;
import com.etiya.authservice.service.dtos.requests.LoginRequest;
import com.etiya.authservice.service.dtos.requests.RegisterUserRequest;
import com.etiya.authservice.service.dtos.responses.LoginResponse;
import com.etiya.common.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(RegisterUserRequest request) {
        userService.add(request);

        //Register işlemi için business kuralları tanımlayın
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if(!authentication.isAuthenticated())
            throw new RuntimeException("E posta veya şifre hatalı"); //RuntimeEx türü AuthenticationEx olacak.
        UserDetails user = userService.loadUserByUsername(request.getEmail());
        LoginResponse loginResponse =  new LoginResponse();
        loginResponse.setEmail(user.getUsername());
        loginResponse.setToken(jwtService.generateToken(user.getUsername(),user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()));
        return loginResponse;
    }
}