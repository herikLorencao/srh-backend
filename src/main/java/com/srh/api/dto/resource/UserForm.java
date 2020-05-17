package com.srh.api.dto.resource;

import com.srh.api.builder.UserBuilder;
import com.srh.api.model.TypeUsers;
import com.srh.api.model.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserForm {
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

    public UserForm() {
    }

    public UserForm(@NotEmpty @NotNull @Length(min = 3) String name, @NotEmpty @NotNull @Length(min = 3) String login, @NotEmpty @NotNull @Length(min = 6) String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User build(TypeUsers typeUser) {
        return UserBuilder.anUser()
                .withName(name)
                .withLogin(login)
                .withPassword(password)
                .build(typeUser);
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

}
