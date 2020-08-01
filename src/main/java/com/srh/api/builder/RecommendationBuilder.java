package com.srh.api.builder;

import com.srh.api.model.*;

import java.time.LocalDateTime;
import java.util.List;

public final class RecommendationBuilder {
    private Integer id;
    private Double weight;
    private LocalDateTime date;
    private Integer runtimeInSeconds;
    private Algorithm algorithm;
    private Evaluator evaluator;
    private Item item;
    private List<RecommendationRating> recommendationRatings;
    private RecommendationResultMatrix recommendationResultMatrix;

    private RecommendationBuilder() {
    }

    public static RecommendationBuilder aRecommendation() {
        return new RecommendationBuilder();
    }

    public RecommendationBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public RecommendationBuilder withWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public RecommendationBuilder withDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public RecommendationBuilder withRuntimeInSeconds(Integer runtimeInSeconds) {
        this.runtimeInSeconds = runtimeInSeconds;
        return this;
    }

    public RecommendationBuilder withAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public RecommendationBuilder withEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
        return this;
    }

    public RecommendationBuilder withItem(Item item) {
        this.item = item;
        return this;
    }

    public RecommendationBuilder withRecommendationRatings(List<RecommendationRating> recommendationRatings) {
        this.recommendationRatings = recommendationRatings;
        return this;
    }

    public RecommendationBuilder withRecommendationResult(RecommendationResultMatrix recommendationResultMatrix) {
        this.recommendationResultMatrix = recommendationResultMatrix;
        return this;
    }

    public Recommendation build() {
        Recommendation recommendation = new Recommendation();
        recommendation.setId(id);
        recommendation.setWeight(weight);
        recommendation.setDate(date);
        recommendation.setRuntimeInSeconds(runtimeInSeconds);
        recommendation.setAlgorithm(algorithm);
        recommendation.setEvaluator(evaluator);
        recommendation.setItem(item);
        recommendation.setRecommendationRatings(recommendationRatings);
        recommendation.setRecommendationResultMatrix(recommendationResultMatrix);
        return recommendation;
    }
}
