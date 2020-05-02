package com.srh.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Rating {
    private static final long serialVersionUID = 1L;
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
}
