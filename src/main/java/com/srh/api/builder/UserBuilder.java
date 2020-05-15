package com.srh.api.builder;

import com.srh.api.model.*;

public final class UserBuilder {
    protected String password;
    private Integer id;
    private String name;
    private String login;
    private TypeUsers profile;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withProfile(TypeUsers profile) {
        this.profile = profile;
        return this;
    }

    public User build(TypeUsers typeUser) {
        User user = UserBuilder.buildUserInstance(typeUser);
        user.setId(id);
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setProfile(profile);
        return user;
    }

    private static User buildUserInstance(TypeUsers typeUser) {
        if (typeUser == TypeUsers.ADMIN) {
            return new Admin();
        } else if (typeUser == TypeUsers.RECOMMENDATION) {
            return new UserRecommendation();
        } else {
            return new ApiUser();
        }
    }
}
