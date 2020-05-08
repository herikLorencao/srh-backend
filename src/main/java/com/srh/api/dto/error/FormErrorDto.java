package com.srh.api.dto.error;

public class FormErrorDto {
    private final String error;
    private final String field;

    public FormErrorDto(String error, String field) {
        this.error = error;
        this.field = field;
    }

    public String getError() {
        return error;
    }

    public String getField() {
        return field;
    }
}
