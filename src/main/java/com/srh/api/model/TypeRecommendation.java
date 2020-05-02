package com.srh.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class TypeRecommendation {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String description;
    private Boolean active;
    @OneToMany(mappedBy = "typeRecommendation")
    private List<Recommendation> recommendations;

    public TypeRecommendation() {
    }

    public TypeRecommendation(Integer id, String name, String description, List<Recommendation> recommendations) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
