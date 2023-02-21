package com.example.ottokeng.domain.find.exceptions;

import com.example.ottokeng.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FindWritingNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public FindWritingNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.FIND_WRITING_NOT_FOUND;
    }
}
