package com.example.ottokeng.global.exception.handler;

import com.example.ottokeng.global.exception.CustomException;
import com.example.ottokeng.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.ottokeng.global.exception.ErrorCode.UNKNOWN_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode(), e);
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(Exception e) {
        log.error("UnknownExceptionHandler throw Exception : {}", e.getMessage(), e);
        return ErrorResponse.toResponseEntity(UNKNOWN_ERROR);
    }

}
