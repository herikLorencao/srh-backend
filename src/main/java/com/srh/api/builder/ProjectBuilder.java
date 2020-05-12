package com.srh.api.builder;

import com.srh.api.model.*;

import java.time.LocalDate;
import java.util.List;

public final class ProjectBuilder {
    private Integer id;
    private String name;
    private String description;
    private LocalDate date;
    private Situations situation;
    private Admin admin;
    private List<UserRecommendation> users;
    private List<Item> itens;

    private ProjectBuilder() {
    }

    public static ProjectBuilder aProject() {
        return new ProjectBuilder();
    }

    public ProjectBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ProjectBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProjectBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProjectBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public ProjectBuilder withSituation(Situations situation) {
        this.situation = situation;
        return this;
    }

    public ProjectBuilder withAdmin(Admin admin) {
        this.admin = admin;
        return this;
    }

    public ProjectBuilder withUsers(List<UserRecommendation> users) {
        this.users = users;
        return this;
    }

    public ProjectBuilder withItens(List<Item> itens) {
        this.itens = itens;
        return this;
    }

    public Project build() {
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setDescription(description);
        project.setDate(date);
        project.setSituation(situation);
        project.setAdmin(admin);
        project.setUsers(users);
        project.setItens(itens);
        return project;
    }
}
