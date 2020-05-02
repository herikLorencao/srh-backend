package com.srh.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "item")
    private List<Rating> ratings;
    @ManyToOne
    private Project project;
    @OneToMany(mappedBy = "id.item")
    private List<ItemTag> itensTag;
    @OneToMany(mappedBy = "item")
    private List<Recommendation> recommendations;

    public Item() {
    }

    public Item(Integer id, String name, String description, List<Rating> ratings, Project project, List<ItemTag> itensTag, List<Recommendation> recommendations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ratings = ratings;
        this.project = project;
        this.itensTag = itensTag;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<ItemTag> getItensTag() {
        return itensTag;
    }

    public void setItensTag(List<ItemTag> itensTag) {
        this.itensTag = itensTag;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}
