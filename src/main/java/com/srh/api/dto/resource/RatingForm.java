package com.srh.api.dto.resource;

import com.srh.api.builder.ItemBuilder;
import com.srh.api.builder.RatingBuilder;
import com.srh.api.builder.RecommenderBuilder;
import com.srh.api.model.Item;
import com.srh.api.model.Rating;
import com.srh.api.model.Recommender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RatingForm {
    @NotNull
    private Double score;
    @NotNull
    private Integer recommenderId;
    @NotNull
    private Integer itemId;

    public Rating build() {
        Recommender recommender = RecommenderBuilder.aRecommender()
                .withId(recommenderId)
                .build();

        Item item = ItemBuilder.anItem()
                .withId(itemId)
                .build();

        return RatingBuilder.aRating()
                .withScore(score)
                .withUser(recommender)
                .withItem(item)
                .withDate(LocalDateTime.now())
                .build();
    }
}
