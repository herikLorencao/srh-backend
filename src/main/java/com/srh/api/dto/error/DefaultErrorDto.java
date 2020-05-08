package com.srh.api.dto.error;

public class DefaultErrorDto {
    private final String error;
    private final String cause;

    public DefaultErrorDto(String error, String cause) {
        this.error = error;
        this.cause = cause;
    }

    public String getError() {
        return error;
    }

    public String getCause() {
        return cause;
    }
}
