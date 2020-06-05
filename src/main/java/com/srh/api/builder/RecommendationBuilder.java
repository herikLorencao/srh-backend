package com.srh.api.builder;

import com.srh.api.model.Item;
import com.srh.api.model.Recommendation;
import com.srh.api.model.Evaluator;
import com.srh.api.model.TypeRecommendation;

import java.time.LocalDateTime;

public final class RecommendationBuilder {
    private Integer id;
    private Double weight;
    private LocalDateTime date;
    private Integer runtimeInSeconds;
    private TypeRecommendation typeRecommendation;
    private Evaluator evaluator;
    private Item item;

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

    public RecommendationBuilder withTypeRecommendation(TypeRecommendation typeRecommendation) {
        this.typeRecommendation = typeRecommendation;
        return this;
    }

    public RecommendationBuilder withRecommender(Evaluator evaluator) {
        this.evaluator = evaluator;
        return this;
    }

    public RecommendationBuilder withItem(Item item) {
        this.item = item;
        return this;
    }

    public Recommendation build() {
        Recommendation recommendation = new Recommendation();
        recommendation.setId(id);
        recommendation.setWeight(weight);
        recommendation.setDate(date);
        recommendation.setRuntimeInSeconds(runtimeInSeconds);
        recommendation.setTypeRecommendation(typeRecommendation);
        recommendation.setEvaluator(evaluator);
        recommendation.setItem(item);
        return recommendation;
    }
}
