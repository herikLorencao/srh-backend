package com.srh.api.builder;

import com.srh.api.model.Recommendation;
import com.srh.api.model.TypeRecommendation;

import java.util.List;

public final class TypeRecommendationBuilder {
    private Integer id;
    private String name;
    private String description;
    private Boolean active;
    private List<Recommendation> recommendations;

    private TypeRecommendationBuilder() {
    }

    public static TypeRecommendationBuilder aTypeRecommendation() {
        return new TypeRecommendationBuilder();
    }

    public TypeRecommendationBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public TypeRecommendationBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TypeRecommendationBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public TypeRecommendationBuilder withActive(Boolean active) {
        this.active = active;
        return this;
    }

    public TypeRecommendationBuilder withRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
        return this;
    }

    public TypeRecommendation build() {
        TypeRecommendation typeRecommendation = new TypeRecommendation();
        typeRecommendation.setId(id);
        typeRecommendation.setName(name);
        typeRecommendation.setDescription(description);
        typeRecommendation.setActive(active);
        typeRecommendation.setRecommendations(recommendations);
        return typeRecommendation;
    }
}
