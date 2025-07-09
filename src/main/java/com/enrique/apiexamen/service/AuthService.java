package com.enrique.apiexamen.service;

import com.enrique.apiexamen.dto.request.AuthRequest;
import com.enrique.apiexamen.dto.response.AuthResponse;
import com.enrique.apiexamen.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    public AuthResponse login(AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = jwtUtils.generateToken(auth.getName());
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
