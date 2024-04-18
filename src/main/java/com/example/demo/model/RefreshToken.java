package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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