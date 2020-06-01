package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double weight;
    private Double score;
    private LocalDateTime date;
    private Integer runtimeInSeconds;

    @Enumerated(EnumType.STRING)
    private TypeRecommendation typeRecommendation;

    @ManyToOne
    private Recommender recommender;

    @ManyToOne
    private Item item;
}
