package com.srh.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends DefaultEntity {
    private String name;
    @Column(unique = true)
    private String login;
    protected String password;
    @Enumerated(EnumType.STRING)
    private Profiles profile;

    public User() {
    }

    public User(Integer id, String name, String login, String password, Profiles profile) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }

    public Profiles getProfile() {
        return profile;
    }

    public void setProfile(Profiles profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                profile == user.profile;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, login, password, profile);
    }
}
