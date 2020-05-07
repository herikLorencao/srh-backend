package com.srh.api.service;

import com.srh.api.model.UserApi;
import com.srh.api.repository.UserApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserApiRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserApi> user = repository.findByLogin(login);
        if (user.isPresent())
            return user.get();

        throw new UsernameNotFoundException("Invalid user data");
    }
}
