package com.example.scheduleproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidExceptionHandler {

    @ExceptionHandler(InvalidHandler.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidHandler ex) {
        // 예외 발생 시 적절한 응답을 반환
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);  // 400 Bad Request
    }

    @ExceptionHandler(InvalidHandler.class)
    public ResponseEntity<String> handleInvalidUserInformationException(InvalidHandler ex) {
        // 예외 발생 시 적절한 응답을 반환
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);  // 400 Bad Request
    }

    @ExceptionHandler(InvalidHandler.class)
    public ResponseEntity<String> handleInvalidScheduleException(InvalidHandler ex) {
        // 예외 발생 시 적절한 응답을 반환
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);  // 400 Bad Request
    }

    @ExceptionHandler(InvalidHandler.class)
    public ResponseEntity<String> handleValidationExceptions(InvalidHandler ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
    }

}
