package com.srh.api.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class Recommender extends User {
    @ManyToMany
    private List<Project> projects;
    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    public Recommender() {
        this.setProfile(TypeUsers.RECOMMENDATION);
    }

    public Recommender(Integer id, String name, String login, String password,
                       List<Project> projects, List<Rating> ratings) {
        super(id, name, login, password, TypeUsers.RECOMMENDATION);
        this.projects = projects;
        this.ratings = ratings;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Recommender that = (Recommender) o;
        return Objects.equals(projects, that.projects) &&
                Objects.equals(ratings, that.ratings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), projects, ratings);
    }
}
