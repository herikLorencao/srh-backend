package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
@Data
public class TypeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private HashMap<String, String> attributes;

    @OneToMany(mappedBy = "typeItem")
    private List<Item> itens;
}