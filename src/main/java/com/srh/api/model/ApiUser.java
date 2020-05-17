package com.srh.api.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class ApiUser extends User implements UserDetails {
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profiles;
    private boolean isAdmin;

    public ApiUser() {
        this.setProfile(TypeUsers.API);
    }

    public ApiUser(Integer id, String name, String login, String password, TypeUsers profile, List<Profile> profiles, boolean isAdmin) {
        super(id, name, login, password, profile);
        this.setProfile(TypeUsers.API);
        this.profiles = profiles;
        this.isAdmin = isAdmin;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ApiUser apiUser = (ApiUser) o;
        return isAdmin == apiUser.isAdmin &&
                Objects.equals(profiles, apiUser.profiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), profiles, isAdmin);
    }
}
