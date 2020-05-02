package com.srh.api.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Admin extends User {
    private static final long serialVersionUID = 1L;
    @OneToMany(mappedBy = "admin")
    private List<Project> projects;

    public Admin() {
        this.setProfile(Profiles.ADMIN);
    }

    public Admin(Integer id, String name, String login, String password, List<Project> projects) {
        super(id, name, login, password, Profiles.ADMIN);
        this.projects = projects;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
