package com.srh.api.dto.resource;

import com.srh.api.model.UserApi;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class UserApiDto extends RepresentationModel<UserApiDto> {
    private final Integer id;
    private final String name;

    public UserApiDto(UserApi userApi) {
        this.id = userApi.getId();
        this.name = userApi.getName();
    }

    public static Page<UserApiDto> convert(Page<UserApi> users) {
        return users.map(UserApiDto::new);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserApiDto that = (UserApiDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name);
    }
}
