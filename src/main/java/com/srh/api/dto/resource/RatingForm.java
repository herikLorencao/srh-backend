package com.srh.api.dto.resource;

import com.srh.api.builder.RatingBuilder;
import com.srh.api.model.Rating;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RatingForm {
    @NotNull
    private Double score;

    public RatingForm() {
    }

    public RatingForm(@NotEmpty @NotNull Double score) {
        this.score = score;
    }

    public Double getScore() {
        return score;
    }

    public Rating build() {
        return RatingBuilder.aRating()
                .withScore(score)
                .withDate(LocalDateTime.now())
                .build();
    }
}
