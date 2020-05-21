package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import com.srh.api.error.exception.DuplicateValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class DuplicateValueHandler {
    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(DuplicateValueException.class)
    public DefaultErrorDto handle() {
        return new DefaultErrorDto(
                "The link between resources already exists",
                "Duplicate values in resources"
        );
    }
}
