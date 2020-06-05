package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double weight;
    private LocalDateTime date;
    private Integer runtimeInSeconds;

    @Enumerated(EnumType.STRING)
    private TypeRecommendation typeRecommendation;

    @ManyToOne
    private Evaluator evaluator;

    @ManyToOne
    private Item item;

    @OneToMany(mappedBy = "recommendation")
    private List<RecommendationRating> recommendationRatings;
}
