package com.srh.api.builder;

import com.srh.api.model.Admin;
import com.srh.api.model.Project;
import com.srh.api.model.TypeUsers;

import java.util.List;

public final class AdminBuilder {
    protected String password;
    private List<Project> projects;
    private Integer id;
    private String name;
    private String login;
    private TypeUsers profile;

    private AdminBuilder() {
    }

    public static AdminBuilder anAdmin() {
        return new AdminBuilder();
    }

    public AdminBuilder withProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }

    public AdminBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public AdminBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AdminBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public AdminBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public AdminBuilder withProfile(TypeUsers profile) {
        this.profile = profile;
        return this;
    }

    public Admin build() {
        Admin admin = new Admin();
        admin.setProjects(projects);
        admin.setId(id);
        admin.setName(name);
        admin.setLogin(login);
        admin.setPassword(password);
        admin.setProfile(profile);
        return admin;
    }
}
