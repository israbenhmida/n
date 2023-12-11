package com.example.projet.Auth.jwt;

import lombok.Data;

@Data
public class JWTAuthenticationRequest {
    private String email;
    private String password;
}