package com.srh.api.dto.resource;

import com.srh.api.model.Rating;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingDto ratingDto = (RatingDto) o;
        return Objects.equals(id, ratingDto.id) &&
                Objects.equals(score, ratingDto.score) &&
                Objects.equals(date, ratingDto.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, date);
    }
}
