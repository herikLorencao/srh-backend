package com.srh.api.dto.resource;

import com.srh.api.builder.UserBuilder;
import com.srh.api.model.TypeUsers;
import com.srh.api.model.User;

public class UserForm {
    private String name;
    private String login;
    private String password;

    public UserForm() {
    }

    public UserForm(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
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

    public User build(TypeUsers typeUser) {
        return UserBuilder.anUser()
                .withName(name)
                .withLogin(login)
                .withPassword(password)
                .build(typeUser);
    }
}
