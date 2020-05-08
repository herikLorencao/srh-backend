package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import com.srh.api.error.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidTokenHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTokenException.class)
    public DefaultErrorDto handle() {
        return new DefaultErrorDto(
                "The token is not valid",
                "the reported token is in the wrong format");
    }
}
