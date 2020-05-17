package com.srh.api.dto.resource;

import com.srh.api.model.Profile;

public class ProfileDto {
    private final String authority;

    public ProfileDto(Profile profile) {
        this.authority = profile.getAuthority();
    }

    public String getAuthority() {
        return authority;
    }
}
