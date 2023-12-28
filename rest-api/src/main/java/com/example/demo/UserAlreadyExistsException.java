package com.example.demo;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(Long id) {
        super("User already exists: " + id);
    }
}
