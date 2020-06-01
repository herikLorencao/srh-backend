package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "item")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "item")
    private List<Recommendation> recommendations;

    @ManyToOne
    private Project project;

    @ManyToMany(mappedBy = "itens")
    private List<Tag> tags;
}
