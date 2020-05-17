package com.srh.api.dto.resource;

import com.srh.api.model.TypeRecommendation;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "typeRecommendations")
public class TypeRecommendationDto {
    private final Integer id;
    private final String name;
    private final String description;
    private final boolean active;

    public TypeRecommendationDto(TypeRecommendation typeRecommendation) {
        this.id = typeRecommendation.getId();
        this.name = typeRecommendation.getName();
        this.description = typeRecommendation.getDescription();
        this.active = typeRecommendation.getActive();
    }

    public static Page<TypeRecommendationDto> convert(Page<TypeRecommendation> typeRecommendations) {
        return typeRecommendations.map(TypeRecommendationDto::new);
    }

    public Integer getId() {
        return id;
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
