package com.srh.api.dto.resource;

import com.srh.api.model.Recommendation;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.util.Objects;

@Relation(collectionRelation = "recommendationUsers")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendationDto that = (RecommendationDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(score, that.score) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weight, score, date);
    }
}
