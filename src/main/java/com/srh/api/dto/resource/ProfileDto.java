package com.srh.api.dto.resource;

import com.srh.api.model.Profile;

import java.util.Objects;

public class ProfileDto {
    private final String authority;

    public ProfileDto(Profile profile) {
        this.authority = profile.getAuthority();
    }

    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDto that = (ProfileDto) o;
        return Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }
}
