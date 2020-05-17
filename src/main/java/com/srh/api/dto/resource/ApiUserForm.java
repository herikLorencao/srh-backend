package com.srh.api.dto.resource;

import com.srh.api.builder.ApiUserBuilder;
import com.srh.api.model.ApiUser;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ApiUserForm {
    @NotEmpty
    @NotNull
    @Length(min = 3)
    private String name;
    @NotEmpty
    @NotNull
    @Length(min = 3)
    private String login;
    @NotEmpty
    @NotNull
    @Length(min = 6)
    private String password;
    @NotNull
    private boolean isAdmin;

    public ApiUserForm() {
    }

    public ApiUserForm(@NotEmpty @NotNull @Length(min = 3) String name,
                       @NotEmpty @NotNull @Length(min = 3) String login,
                       @NotEmpty @NotNull @Length(min = 6) String password,
                       @NotNull boolean isAdmin) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public ApiUser build() {
        return ApiUserBuilder.anApiUser()
                .withLogin(login)
                .withName(name)
                .withPassword(new BCryptPasswordEncoder().encode(password))
                .withIsAdmin(isAdmin)
                .build();
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
