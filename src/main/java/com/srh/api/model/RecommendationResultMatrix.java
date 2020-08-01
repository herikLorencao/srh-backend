package com.srh.api.model;

import com.srh.api.algorithms.structure.RecommendationMatrix;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class RecommendationResultMatrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "recommendationResultMatrix", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommendation> recommendation;

    @ManyToOne
    private Algorithm algorithm;

    @OneToOne
    private RecommendationMatrix result;

    private Double passingScore;
    private Boolean offline;
}
