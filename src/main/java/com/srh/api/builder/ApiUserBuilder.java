package com.srh.api.builder;

import com.srh.api.model.ApiUser;
import com.srh.api.model.Profile;
import com.srh.api.model.TypeUsers;

import java.util.List;

public final class ApiUserBuilder {
    protected String password;
    private List<Profile> profiles;
    private boolean isAdmin;
    private Integer id;
    private String name;
    private String login;
    private TypeUsers profile;

    private ApiUserBuilder() {
    }

    public static ApiUserBuilder anApiUser() {
        return new ApiUserBuilder();
    }

    public ApiUserBuilder withProfiles(List<Profile> profiles) {
        this.profiles = profiles;
        return this;
    }

    public ApiUserBuilder withIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public ApiUserBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ApiUserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ApiUserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public ApiUserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public ApiUserBuilder withProfile(TypeUsers profile) {
        this.profile = profile;
        return this;
    }

    public ApiUser build() {
        return new ApiUser(id, name, login, password, profile, profiles, isAdmin);
    }
}
