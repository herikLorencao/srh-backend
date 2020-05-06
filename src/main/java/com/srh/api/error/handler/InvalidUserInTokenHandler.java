package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import com.srh.api.error.exception.InvalidUserInTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidUserInTokenHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidUserInTokenException.class)
    public DefaultErrorDto handle() {
        return new DefaultErrorDto(
                "The user informated is not valid",
                "Inform a user registered in the api"
        );
    }
}
