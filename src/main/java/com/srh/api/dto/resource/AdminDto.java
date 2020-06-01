package com.srh.api.dto.resource;

import com.srh.api.model.Admin;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "admins")
public class AdminDto {
    private final Integer id;
    private final String name;
    private final String login;
    private final String email;

    public AdminDto(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.login = admin.getLogin();
        this.email = admin.getEmail();
    }

    public static Page<AdminDto> convert(Page<Admin> admins) {
        return admins.map(AdminDto::new);
    }
}
