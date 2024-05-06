package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.Instant;

@Entity(name = "refresh_token")
@Data
public class RefreshToken extends BaseIdEntity {
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;
}