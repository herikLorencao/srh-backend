package com.srh.api.builder;

import com.srh.api.model.Project;
import com.srh.api.model.Rating;
import com.srh.api.model.Recommender;
import com.srh.api.model.TypeUsers;

import java.util.List;

public final class RecommenderBuilder {
    protected String password;
    private List<Project> projects;
    private List<Rating> ratings;
    private Integer id;
    private String name;
    private String login;
    private TypeUsers profile;

    private RecommenderBuilder() {
    }

    public static RecommenderBuilder aRecommender() {
        return new RecommenderBuilder();
    }

    public RecommenderBuilder withProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }

    public RecommenderBuilder withRatings(List<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }

    public RecommenderBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public RecommenderBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RecommenderBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public RecommenderBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public RecommenderBuilder withProfile(TypeUsers profile) {
        this.profile = profile;
        return this;
    }

    public Recommender build() {
        Recommender recommender = new Recommender();
        recommender.setProjects(projects);
        recommender.setRatings(ratings);
        recommender.setId(id);
        recommender.setName(name);
        recommender.setLogin(login);
        recommender.setPassword(password);
        recommender.setProfile(profile);
        return recommender;
    }
}
