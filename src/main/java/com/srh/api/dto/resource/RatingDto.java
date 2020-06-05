package com.srh.api.dto.resource;

import com.srh.api.model.Item;
import com.srh.api.model.ItemRating;
import com.srh.api.model.Evaluator;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Getter
@Relation(collectionRelation = "ratings")
public class RatingDto {
    private final Integer id;
    private final Double score;
    private final Evaluator evaluator;
    private final Item item;
    private final LocalDateTime date;

    public RatingDto(ItemRating itemRating) {
        this.id = itemRating.getId();
        this.score = itemRating.getScore();
        this.evaluator = itemRating.getUser();
        this.item = itemRating.getItem();
        this.date = itemRating.getDate();
    }

    public static Page<RatingDto> convert(Page<ItemRating> ratings) {
        return ratings.map(RatingDto::new);
    }
}
