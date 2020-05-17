package com.srh.api.dto.resource;

import com.srh.api.model.ApiUser;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "apiUsers")
public class ApiUsersDto {
    private final Integer id;
    private final String name;
    private final String login;
    private final boolean isAdmin;

    public ApiUsersDto(ApiUser apiUser) {
        this.id = apiUser.getId();
        this.name = apiUser.getName();
        this.login = apiUser.getLogin();
        this.isAdmin = apiUser.isAdmin();
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

    public String getLogin() {
        return login;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
