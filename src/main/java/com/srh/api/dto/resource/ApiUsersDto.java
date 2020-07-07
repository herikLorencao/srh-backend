package com.srh.api.dto.resource;

import com.srh.api.model.ApiUser;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "apiUsers")
public class ApiUsersDto {
    private final Integer id;
    private final String name;
    private final String login;

    public ApiUsersDto(ApiUser apiUser) {
        this.id = apiUser.getId();
        this.name = apiUser.getName();
        this.login = apiUser.getLogin();
    }

    public static Page<ApiUsersDto> convert(Page<ApiUser> users) {
        return users.map(ApiUsersDto::new);
    }
}
