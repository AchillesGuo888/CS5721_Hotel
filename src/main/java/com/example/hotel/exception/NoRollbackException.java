package com.example.hotel.exception;


public class NoRollbackException extends RuntimeException {
    private int code;

    public NoRollbackException(int code, String message){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
