package com.srh.api.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
public class Profile implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany
    private List<ApiUser> apiUsers;

    public Profile() {
    }

    public Profile(Integer id, String name, List<ApiUser> apiUsers) {
        this.id = id;
        this.name = name;
        this.apiUsers = apiUsers;
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

    public List<ApiUser> getApiUsers() {
        return apiUsers;
    }

    public void setApiUsers(List<ApiUser> apiUsers) {
        this.apiUsers = apiUsers;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
