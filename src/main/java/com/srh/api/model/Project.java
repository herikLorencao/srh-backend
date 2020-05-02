package com.srh.api.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Project {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String description;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Situations situation;
    @ManyToOne
    private Admin admin;
    @ManyToMany(mappedBy = "projects")
    private List<UserRecommendation> users;
    @OneToMany(mappedBy = "project")
    private List<Item> itens;

    public Project() {
    }

    public Project(Integer id, String description, LocalDate date, Situations situation, Admin admin, List<UserRecommendation> users, List<Item> itens) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.situation = situation;
        this.admin = admin;
        this.users = users;
        this.itens = itens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Situations getSituation() {
        return situation;
    }

    public void setSituation(Situations situation) {
        this.situation = situation;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<UserRecommendation> getUsers() {
        return users;
    }

    public void setUsers(List<UserRecommendation> users) {
        this.users = users;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
