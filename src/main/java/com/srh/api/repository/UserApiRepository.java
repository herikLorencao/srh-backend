package com.srh.api.repository;

import com.srh.api.model.UserApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserApiRepository extends PagingAndSortingRepository<UserApi, Integer> {
    Optional<UserApi> findByLogin(String login);
}
