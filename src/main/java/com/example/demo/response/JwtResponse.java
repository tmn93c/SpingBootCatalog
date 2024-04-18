package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@PackagePrivate
public class JwtResponse {
    String token;
    String type = "Bearer";
    String refreshToken;
    Long id;
    String username;
    String email;
    List<String> roles;
}
