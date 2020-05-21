package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpRequestMethodNotSupportedHandler {
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public DefaultErrorDto handle() {
        return new DefaultErrorDto(
                "The request method not exists",
                "The API not contains the resource searched"
        );
    }
}
