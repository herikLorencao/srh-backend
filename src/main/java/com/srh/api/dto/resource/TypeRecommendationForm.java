package com.srh.api.dto.resource;

import com.srh.api.builder.TypeRecommendationBuilder;
import com.srh.api.model.TypeRecommendation;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TypeRecommendationForm {
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String name;
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String description;
    @NotNull
    private boolean active;

    public TypeRecommendationForm() {
    }

    public TypeRecommendationForm(@NotNull @NotEmpty @Length(min = 3) String name, @NotNull @NotEmpty @Length(min = 3) String description, @NotNull boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public TypeRecommendation build() {
        return TypeRecommendationBuilder.aTypeRecommendation()
                .withName(name)
                .withDescription(description)
                .withActive(active)
                .build();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

}
