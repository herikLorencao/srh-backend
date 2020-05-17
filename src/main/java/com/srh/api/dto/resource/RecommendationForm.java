package com.srh.api.dto.resource;

import com.srh.api.builder.RecommendationBuilder;
import com.srh.api.model.Recommendation;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RecommendationForm {
    @NotNull
    private Double weight;
    @NotNull
    private Double score;

    public RecommendationForm() {
    }

    public RecommendationForm(@NotNull Double weight, @NotNull Double score) {
        this.weight = weight;
        this.score = score;
    }

    public Recommendation build() {
        return RecommendationBuilder.aRecommendation()
                .withWeight(weight)
                .withScore(score)
                .withDate(LocalDateTime.now())
                .build();
    }

    public Double getWeight() {
        return weight;
    }

    public Double getScore() {
        return score;
    }

}
