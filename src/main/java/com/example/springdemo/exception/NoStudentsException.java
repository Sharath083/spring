package com.example.springdemo.exception;

public class NoStudentsException extends RuntimeException {
    public NoStudentsException(String message) {
        super(message);
    }
}