package com.srh.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Evaluator extends User {
    @ManyToMany
    private List<Project> projects;

    @OneToMany(mappedBy = "evaluator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommendation> recommendations;

    @OneToMany(mappedBy = "evaluator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemRating> itemRatings;

    @OneToMany(mappedBy = "evaluator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecommendationRating> recommendationRatings;
}
