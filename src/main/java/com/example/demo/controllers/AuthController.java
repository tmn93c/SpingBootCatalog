package com.example.demo.controllers;

import com.example.demo.exception.AppException;
import com.example.demo.exception.TokenRefreshException;
import com.example.demo.model.RefreshToken;
import com.example.demo.model.RoleModel;
import com.example.demo.model.RoleName;
import com.example.demo.model.UserModel;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.JwtAuthenticationResponse;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.SignUpRequest;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.TokenRefreshRequest;
import com.example.demo.response.TokenRefreshResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.RefreshTokenService;
import com.example.demo.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    final AuthenticationManager authenticationManager;
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;
    final JwtTokenProvider tokenProvider;
    final RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserModel userModel = (UserModel) authentication.getPrincipal();

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userModel.getId());
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(JwtAuthenticationResponse.builder().accessToken(jwt)
                .refreshToken(refreshToken.getToken()).build());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        UserModel user = new UserModel(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword(),false);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        RoleModel userRole = roleRepository.findByName(RoleName.ROLE_USER.name())
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        UserModel result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = tokenProvider.generateToken(authentication);
                    return ResponseEntity.ok(TokenRefreshResponse.builder()
                            .accessToken(token)
                            .refreshToken(requestRefreshToken)
                            .build());
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
