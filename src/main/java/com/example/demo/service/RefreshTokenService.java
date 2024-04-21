package com.example.demo.service;

import com.example.demo.exception.TokenRefreshException;
import com.example.demo.model.RefreshToken;
import com.example.demo.repository.RefreshTokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@PackagePrivate
public class RefreshTokenService {
    final RefreshTokenRepository refreshTokenRepository;
    final UserRepository userRepository;
    final JwtTokenProvider tokenProvider;
    @Value("${app.jwtRefreshExpirationMs}")
    Long refreshTokenDurationMs;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).orElseThrow());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    public String getAccessToken(String refreshToken) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return findByToken(refreshToken)
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = null;
                    try {
                        token = tokenProvider.generateToken(authentication);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return token;
                }).orElseThrow(() -> new TokenRefreshException(refreshToken,
                        "Refresh token is not in database!"));
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).orElseThrow());
    }
}
