package com.srh.api.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
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

    public Project(Integer id, String name, String description, LocalDate date, Situations situation, Admin admin, List<UserRecommendation> users, List<Item> itens) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(date, project.date) &&
                situation == project.situation &&
                Objects.equals(admin, project.admin) &&
                Objects.equals(users, project.users) &&
                Objects.equals(itens, project.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, date, situation, admin, users, itens);
    }
}
