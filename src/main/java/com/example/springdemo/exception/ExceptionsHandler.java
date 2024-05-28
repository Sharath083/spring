package com.example.springdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionsHandler extends RuntimeException{
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommonException.class)
    public ExceptionData throwException(CommonException ex){
        return new ExceptionData("001",ex.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionData throwExceptionInvalidInput(MethodArgumentNotValidException ex){
        return new ExceptionData("001",
                ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }


}

