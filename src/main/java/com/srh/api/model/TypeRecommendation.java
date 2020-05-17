package com.srh.api.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class TypeRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Boolean active;
    @OneToMany(mappedBy = "typeRecommendation")
    private List<Recommendation> recommendations;

    public TypeRecommendation() {
    }

    public TypeRecommendation(Integer id, String name, String description, Boolean active, List<Recommendation> recommendations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.recommendations = recommendations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        TypeRecommendation that = (TypeRecommendation) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(active, that.active) &&
                Objects.equals(recommendations, that.recommendations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, active, recommendations);
    }
}
