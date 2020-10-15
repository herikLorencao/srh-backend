package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private LocalDate date;
    private Boolean visible;
    private Integer lastMatrixId;

    @Enumerated(EnumType.STRING)
    private Situations situation;

    @ManyToOne
    private Admin admin;

    @ManyToMany(mappedBy = "projects")
    private List<Evaluator> evaluators;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens;
}
