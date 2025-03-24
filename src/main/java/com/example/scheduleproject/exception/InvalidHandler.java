package com.example.scheduleproject.exception;

import lombok.Getter;

public class InvalidHandler extends RuntimeException {

    private final String erroryType;

    public InvalidHandler(String errorType, String message) {
        super(message);
        this.erroryType = errorType;
    }

    public String getErroryType() {
        return erroryType;
    }

}
