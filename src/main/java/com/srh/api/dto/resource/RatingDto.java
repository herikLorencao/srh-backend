package com.srh.api.dto.resource;

import com.srh.api.model.Rating;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class RatingDto {
    private final Integer id;
    private final Double score;
    private final LocalDateTime date;

    public RatingDto(Rating rating) {
        this.id = rating.getId();
        this.score = rating.getScore();
        this.date = rating.getDate();
    }

    public static Page<RatingDto> convert(Page<Rating> ratings) {
        return ratings.map(RatingDto::new);
    }

    public Integer getId() {
        return id;
    }

    public Double getScore() {
        return score;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
