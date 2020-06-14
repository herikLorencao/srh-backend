package com.srh.api.dto.resource;

import com.srh.api.model.Rating;
import com.srh.api.model.RecommendationRating;

public class RecommendationRatingDto extends RatingDto{
    public RecommendationRatingDto(RecommendationRating recommedationRating) {
        super(recommedationRating);
    }
}
