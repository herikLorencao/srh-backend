package com.srh.api.builder;

import com.srh.api.model.Item;
import com.srh.api.model.Rating;
import com.srh.api.model.Recommender;

import java.time.LocalDateTime;

public final class RatingBuilder {
    private Integer id;
    private Double score;
    private LocalDateTime date;
    private Recommender user;
    private Item item;

    private RatingBuilder() {
    }

    public static RatingBuilder aRating() {
        return new RatingBuilder();
    }

    public RatingBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public RatingBuilder withScore(Double score) {
        this.score = score;
        return this;
    }

    public RatingBuilder withDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public RatingBuilder withUser(Recommender user) {
        this.user = user;
        return this;
    }

    public RatingBuilder withItem(Item item) {
        this.item = item;
        return this;
    }

    public Rating build() {
        Rating rating = new Rating();
        rating.setId(id);
        rating.setScore(score);
        rating.setDate(date);
        rating.setUser(user);
        rating.setItem(item);
        return rating;
    }
}
