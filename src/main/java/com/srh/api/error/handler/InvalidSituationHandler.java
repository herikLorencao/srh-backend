package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import com.srh.api.error.exception.InvalidSituationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidSituationHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidSituationException.class)
    public DefaultErrorDto handle() {
        return new DefaultErrorDto(
                "Invalid situation value",
                "The allowed values are 'O' and 'C'"
        );
    }
}
