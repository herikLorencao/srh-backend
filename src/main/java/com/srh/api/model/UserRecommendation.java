package com.srh.api.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class UserRecommendation extends User {
    @ManyToMany
    private List<Project> projects;
    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;
    @OneToMany(mappedBy = "user")
    private List<Recommendation> recommendations;

    public UserRecommendation() {
        this.setProfile(TypeUsers.RECOMMENDATION);
    }

    public UserRecommendation(Integer id, String name, String login, String password, List<Project> projects, List<Rating> ratings, List<Recommendation> recommendations) {
        super(id, name, login, password, TypeUsers.RECOMMENDATION);
        this.projects = projects;
        this.ratings = ratings;
        this.recommendations = recommendations;
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

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserRecommendation that = (UserRecommendation) o;
        return Objects.equals(projects, that.projects) &&
                Objects.equals(ratings, that.ratings) &&
                Objects.equals(recommendations, that.recommendations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), projects, ratings, recommendations);
    }
}
