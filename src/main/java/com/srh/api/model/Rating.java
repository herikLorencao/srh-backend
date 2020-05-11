package com.srh.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Double score;
    private LocalDateTime date;
    @ManyToOne
    private UserRecommendation user;
    @ManyToOne
    private Item item;

    public Rating() {
    }

    public Rating(Integer id, Double score, LocalDateTime date, UserRecommendation user, Item item) {
        this.id = id;
        this.score = score;
        this.date = date;
        this.user = user;
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UserRecommendation getUser() {
        return user;
    }

    public void setUser(UserRecommendation user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rating rating = (Rating) o;
        return Objects.equals(score, rating.score) &&
                Objects.equals(date, rating.date) &&
                Objects.equals(user, rating.user) &&
                Objects.equals(item, rating.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), score, date, user, item);
    }
}
