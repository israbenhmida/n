package com.example.projet.Auth.exception;

/**
 * @author Sampson Alfred
 */

public class InvalidVerificationTokenException extends RuntimeException {
    public InvalidVerificationTokenException(String message) {
        super(message);
    }
}
