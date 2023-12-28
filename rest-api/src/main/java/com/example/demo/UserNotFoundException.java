package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotFoundException extends RuntimeException {

    private Long id;
    public UserNotFoundException(Long id) {
        super("Could not find user with id: " + id);
    }
}
