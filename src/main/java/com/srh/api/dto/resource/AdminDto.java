package com.srh.api.dto.resource;

import com.srh.api.model.Admin;
import org.springframework.data.domain.Page;

import java.util.Objects;

public class AdminDto {
    private final Integer id;
    private final String name;
    private final String login;

    public AdminDto(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.login = admin.getLogin();
    }

    public static Page<AdminDto> convert(Page<Admin> admins) {
        return admins.map(AdminDto::new);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminDto adminDto = (AdminDto) o;
        return Objects.equals(id, adminDto.id) &&
                Objects.equals(name, adminDto.name) &&
                Objects.equals(login, adminDto.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login);
    }
}
