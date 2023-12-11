package com.example.projet.Auth.registration;

/**
 * @author Sampson Alfred
 */

public record RegistrationRequest(
         String firstName,
         String lastName,
         String email,
         String password,
         String role) {
}
