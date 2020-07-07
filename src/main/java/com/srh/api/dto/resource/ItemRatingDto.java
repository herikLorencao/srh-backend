package com.srh.api.dto.resource;

import com.srh.api.model.ItemRating;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "itemRatings")
public class ItemRatingDto extends RatingDto{
    public ItemRatingDto(ItemRating itemRating) {
        super(itemRating);
    }
}
