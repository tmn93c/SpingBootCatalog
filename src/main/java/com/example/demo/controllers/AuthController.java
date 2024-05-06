package com.example.demo.controllers;

import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.SignUpRequest;
import com.example.demo.request.TokenRefreshRequest;
import com.example.demo.response.TokenRefreshResponse;
import com.example.demo.service.RefreshTokenService;
import com.example.demo.service.SecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    final RefreshTokenService refreshTokenService;
    final SecurityService securityService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok(securityService.authenticate(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(securityService.register(signUpRequest));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(TokenRefreshResponse.builder()
                            .accessToken(refreshTokenService.getAccessToken(request.getRefreshToken()))
                            .refreshToken(request.getRefreshToken())
                            .build());

    }
}
