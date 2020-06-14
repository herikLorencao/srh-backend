package com.srh.api.dto.resource;

import com.srh.api.model.RecommendationRating;

import javax.validation.constraints.NotNull;

public class RecommendationRatingForm {
    @NotNull
    private Double score;
    @NotNull
    private Integer recommenderId;
    @NotNull
    private Integer itemId;

    public RecommendationRating build() {
        return null;
    }
}
