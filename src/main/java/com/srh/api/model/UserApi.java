package com.srh.api.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.List;

@Entity
public class UserApi extends User implements UserDetails {
    private static final long serialVersionUID = 1L;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profiles;

    public UserApi() {
        this.setProfile(TypeUsers.API);
    }

    public UserApi(Integer id, String name, String login, String password, List<Profile> profiles) {
        super(id, name, login, password, TypeUsers.API);
        this.profiles = profiles;
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
}
