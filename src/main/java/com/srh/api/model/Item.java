package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemRating> itemRatings;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommendation> recommendations;

    @ManyToOne
    private Project project;

    @ManyToOne
    private TypeItem typeItem;

    @ManyToMany(mappedBy = "itens")
    private List<Tag> tags;

    @ManyToMany
    private List<Attribute> attributes;
}
