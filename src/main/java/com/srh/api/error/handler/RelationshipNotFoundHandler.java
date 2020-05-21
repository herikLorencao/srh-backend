package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import com.srh.api.error.exception.RelationshipNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RelationshipNotFoundHandler {
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RelationshipNotFoundException.class)
    public DefaultErrorDto handle() {
        return new DefaultErrorDto(
                "The relationship not exist",
                "Inform a valid relationship"
        );
    }
}
