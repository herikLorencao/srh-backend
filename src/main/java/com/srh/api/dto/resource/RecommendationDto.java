package com.srh.api.dto.resource;

import com.srh.api.model.Recommendation;
import com.srh.api.model.TypeRecommendation;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Getter
@Relation(collectionRelation = "recommendations")
public class RecommendationDto {
    private final Integer id;
    private final Double weight;
    private final LocalDateTime date;
    private final Integer runtimeInSeconds;
    private final TypeRecommendation typeRecommendation;

    public RecommendationDto(Recommendation recommendation) {
        this.id = recommendation.getId();
        this.weight = recommendation.getWeight();
        this.date = recommendation.getDate();
        this.runtimeInSeconds = recommendation.getRuntimeInSeconds();
        this.typeRecommendation = recommendation.getTypeRecommendation();
    }

    public static Page<RecommendationDto> convert(Page<Recommendation> recommendations) {
        return recommendations.map(RecommendationDto::new);
    }
}
