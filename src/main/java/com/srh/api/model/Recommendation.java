package com.srh.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Recommendation that = (Recommendation) o;
        return Objects.equals(weight, that.weight) &&
                Objects.equals(score, that.score) &&
                Objects.equals(date, that.date) &&
                Objects.equals(user, that.user) &&
                Objects.equals(item, that.item) &&
                Objects.equals(typeRecommendation, that.typeRecommendation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight, score, date, user, item, typeRecommendation);
    }
}
