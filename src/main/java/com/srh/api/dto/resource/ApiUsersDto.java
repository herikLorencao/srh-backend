package com.srh.api.dto.resource;

import com.srh.api.model.ApiUser;
import com.srh.api.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Relation(collectionRelation = "apiUsers")
public class ApiUsersDto {
    private final Integer id;
    private final String name;
    private final List<Profile> profiles;

    public ApiUsersDto(ApiUser apiUser) {
        this.id = apiUser.getId();
        this.name = apiUser.getName();
        this.profiles = apiUser.getProfiles();
    }

    public static Page<ApiUsersDto> convert(Page<ApiUser> users) {
        return users.map(ApiUsersDto::new);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ProfileDto> getAuthorities() {
        List<ProfileDto> dtoList = new ArrayList<>();
        profiles.forEach(profile -> {
            ProfileDto profileDto = new ProfileDto(profile);
            dtoList.add(profileDto);
        });
        return dtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiUsersDto that = (ApiUsersDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(profiles, that.profiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, profiles);
    }
}
