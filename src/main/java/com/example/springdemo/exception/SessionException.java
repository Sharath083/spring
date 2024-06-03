package com.example.springdemo.exception;

import org.springframework.security.core.AuthenticationException;

public class SessionException extends AuthenticationException {
    public SessionException(String msg) {
        super(msg);
    }
}
