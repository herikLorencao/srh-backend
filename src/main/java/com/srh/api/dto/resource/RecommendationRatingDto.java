package com.srh.api.dto.resource;

import com.srh.api.model.RecommendationRating;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "recommendationRatings")
public class RecommendationRatingDto extends RatingDto {
    public RecommendationRatingDto(RecommendationRating recommedationRating) {
        super(recommedationRating);
    }
}
