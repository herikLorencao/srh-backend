package com.srh.api.dto.auth;

public class AuthDto {
    private final String token;
    private final String type;

    public AuthDto(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
