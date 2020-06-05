package com.srh.api.builder;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Item;
import com.srh.api.model.ItemRating;

import java.time.LocalDateTime;

public final class ItemRatingBuilder {
    private Integer id;
    private Double score;
    private LocalDateTime date;
    private Evaluator user;
    private Item item;

    private ItemRatingBuilder() {
    }

    public static ItemRatingBuilder anItemRating() {
        return new ItemRatingBuilder();
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

    public ItemRatingBuilder withUser(Evaluator user) {
        this.user = user;
        return this;
    }

    public ItemRatingBuilder withItem(Item item) {
        this.item = item;
        return this;
    }

    public ItemRating build() {
        ItemRating itemRating = new ItemRating();
        itemRating.setId(id);
        itemRating.setScore(score);
        itemRating.setDate(date);
        itemRating.setUser(user);
        itemRating.setItem(item);
        return itemRating;
    }
}
