package com.srh.api.repository;

import com.srh.api.model.UserApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserApiRepository extends JpaRepository<UserApi, Integer> {
    Optional<UserApi> findByLogin(String login);
}
