package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double score;
    private LocalDateTime date;

    @ManyToOne
    private Recommender user;

    @ManyToOne
    private Item item;
}
