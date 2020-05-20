package com.srh.api.dto.resource;

import com.srh.api.model.Recommendation;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Relation(collectionRelation = "recommendations")
public class RecommendationDto {
    private final Integer id;
    private final Double weight;
    private final Double score;
    private final LocalDateTime date;

    public RecommendationDto(Recommendation recommendation) {
        this.id = recommendation.getId();
        this.weight = recommendation.getWeight();
        this.score = recommendation.getScore();
        this.date = recommendation.getDate();
    }

    public static Page<RecommendationDto> convert(Page<Recommendation> recommendations) {
        return recommendations.map(RecommendationDto::new);
    }

    public Integer getId() {
        return id;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getScore() {
        return score;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
