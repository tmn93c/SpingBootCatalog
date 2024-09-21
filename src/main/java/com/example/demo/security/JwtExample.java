package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class JwtExample {
    public static void main(String[] args) {
        // Tạo header
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        // Tạo JWT
        String jwt = Jwts.builder()
                .setHeader(header)
                .setSubject("user@example.com")
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();

        System.out.println("Generated JWT: " + jwt);
    }
}