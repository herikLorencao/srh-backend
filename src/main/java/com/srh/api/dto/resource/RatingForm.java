package com.srh.api.dto.resource;

import com.srh.api.builder.ItemBuilder;
import com.srh.api.builder.RatingBuilder;
import com.srh.api.builder.RecommenderBuilder;
import com.srh.api.model.Item;
import com.srh.api.model.Rating;
import com.srh.api.model.Recommender;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RatingForm {
    @NotNull
    private Double score;
    @NotNull
    private Integer recommenderId;
    @NotNull
    private Integer itemId;

    public RatingForm() {
    }

    public RatingForm(@NotNull Double score, @NotNull Integer recommenderId, @NotNull Integer itemId) {
        this.score = score;
        this.recommenderId = recommenderId;
        this.itemId = itemId;
    }

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

    public Integer getItemId() {
        return itemId;
    }

    public Double getScore() {
        return score;
    }

    public Integer getRecommenderId() {
        return recommenderId;
    }
}
