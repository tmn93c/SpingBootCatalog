package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

@Data
@PackagePrivate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRefreshResponse {
    String accessToken;
    String refreshToken;
}
