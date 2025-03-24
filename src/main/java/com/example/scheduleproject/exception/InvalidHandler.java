package com.example.scheduleproject.exception;

import lombok.Getter;

public class InvalidHandler extends RuntimeException {

    private final String erroryType;

    /**
     * 공통 예외 처리를 위한 에러 타입과 에러 메세지 전달
     * @param errorType
     * @param message
     */
    public InvalidHandler(String errorType, String message) {
        super(message);
        this.erroryType = errorType;
    }

    public String getErroryType() {
        return erroryType;
    }

}
