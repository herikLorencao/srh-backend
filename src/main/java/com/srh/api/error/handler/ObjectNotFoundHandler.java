package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ObjectNotFoundHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public DefaultErrorDto handle() {
        return new DefaultErrorDto(
                "The resource is not found",
                "The resource not exist in system"
        );
    }
}
