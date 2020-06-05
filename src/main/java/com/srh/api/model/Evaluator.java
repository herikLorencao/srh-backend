package com.srh.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
    
    @OneToMany(mappedBy = "user")
    private List<ItemRating> itemRatings;

    @OneToMany(mappedBy = "evaluator")
    private List<RecommendationRating> recommendationRatings;
}
