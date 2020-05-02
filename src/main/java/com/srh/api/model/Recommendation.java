package com.srh.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Recommendation {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Double weight;
    private Double score;
    private LocalDateTime date;
    @ManyToOne
    private UserRecommendation user;
    @ManyToOne
    private Item item;
    @ManyToOne
    private TypeRecommendation typeRecommendation;

    public Recommendation() {
    }

    public Recommendation(Integer id, Double weight, Double score, LocalDateTime date, UserRecommendation user, Item item, TypeRecommendation typeRecommendation) {
        this.id = id;
        this.weight = weight;
        this.score = score;
        this.date = date;
        this.user = user;
        this.item = item;
        this.typeRecommendation = typeRecommendation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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

    public TypeRecommendation getTypeRecommendation() {
        return typeRecommendation;
    }

    public void setTypeRecommendation(TypeRecommendation typeRecommendation) {
        this.typeRecommendation = typeRecommendation;
    }
}
