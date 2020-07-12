package com.srh.api.dto.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Item;
import com.srh.api.model.ItemRating;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "itemRatings")
public class ItemRatingDto extends RatingDto {
    @JsonIgnore
    private final Item item;
    @JsonIgnore
    private final Evaluator evaluator;

    public ItemRatingDto(ItemRating itemRating) {
        super(itemRating);
        this.item = itemRating.getItem();
        this.evaluator = itemRating.getEvaluator();
    }

    public static Page<ItemRatingDto> convert(Page<ItemRating> itemRatings) {
        return itemRatings.map(ItemRatingDto::new);
    }
}
