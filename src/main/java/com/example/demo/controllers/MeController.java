package com.example.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeController {
    @GetMapping("/me")
    public String getGretting(Authentication auth) {
        return auth.getPrincipal().toString();
    }

}