package com.srh.api.dto.resource;

import com.srh.api.model.Item;
import com.srh.api.model.Rating;
import com.srh.api.model.Recommender;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Getter
@Relation(collectionRelation = "ratings")
public class RatingDto {
    private final Integer id;
    private final Double score;
    private final Recommender recommender;
    private final Item item;
    private final LocalDateTime date;

    public RatingDto(Rating rating) {
        this.id = rating.getId();
        this.score = rating.getScore();
        this.recommender = rating.getUser();
        this.item = rating.getItem();
        this.date = rating.getDate();
    }

    public static Page<RatingDto> convert(Page<Rating> ratings) {
        return ratings.map(RatingDto::new);
    }
}
