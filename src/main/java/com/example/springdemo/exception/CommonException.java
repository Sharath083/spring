package com.example.springdemo.exception;

public class CommonException extends RuntimeException {
    String infoId;
    public CommonException(String infoId,String message) {
        super(message);
        this.infoId=infoId;
    }
}