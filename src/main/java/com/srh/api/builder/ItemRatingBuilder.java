package com.srh.api.builder;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Item;
import com.srh.api.model.ItemRating;

import java.time.LocalDateTime;

public final class ItemRatingBuilder {
    private Evaluator evaluator;
    private Item item;
    private Integer id;
    private Double score;
    private LocalDateTime date;

    private ItemRatingBuilder() {
    }

    public static ItemRatingBuilder anItemRating() {
        return new ItemRatingBuilder();
    }

    public ItemRatingBuilder withEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
        return this;
    }

    public ItemRatingBuilder withItem(Item item) {
        this.item = item;
        return this;
    }

    public ItemRatingBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ItemRatingBuilder withScore(Double score) {
        this.score = score;
        return this;
    }

    public ItemRatingBuilder withDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public ItemRating build() {
        ItemRating itemRating = new ItemRating();
        itemRating.setEvaluator(evaluator);
        itemRating.setItem(item);
        itemRating.setId(id);
        itemRating.setScore(score);
        itemRating.setDate(date);
        return itemRating;
    }
}
