package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PackagePrivate
public class JwtAuthenticationResponse {
    String accessToken;
    String refreshToken;
}
