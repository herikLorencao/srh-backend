package com.srh.api.builder;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Project;
import com.srh.api.model.ItemRating;

import java.util.List;

public final class EvaluatorBuilder {
    protected String password;
    private List<Project> projects;
    private List<ItemRating> itemRatings;
    private Integer id;
    private String login;
    private String oldPassword;
    private String name;
    private String email;

    private EvaluatorBuilder() {
    }

    public static EvaluatorBuilder anEvaluator() {
        return new EvaluatorBuilder();
    }

    public EvaluatorBuilder withProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }

    public EvaluatorBuilder withRatings(List<ItemRating> itemRatings) {
        this.itemRatings = itemRatings;
        return this;
    }

    public EvaluatorBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public EvaluatorBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public EvaluatorBuilder withOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public EvaluatorBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EvaluatorBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public EvaluatorBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public Evaluator build() {
        Evaluator evaluator = new Evaluator();
        evaluator.setProjects(projects);
        evaluator.setItemRatings(itemRatings);
        evaluator.setId(id);
        evaluator.setLogin(login);
        evaluator.setOldPassword(oldPassword);
        evaluator.setName(name);
        evaluator.setEmail(email);
        evaluator.setPassword(password);
        return evaluator;
    }
}
