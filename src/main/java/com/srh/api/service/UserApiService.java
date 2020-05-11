package com.srh.api.service;

import com.srh.api.model.UserApi;
import com.srh.api.repository.UserApiRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApiService {
    @Autowired
    private UserApiRepository userApiRepository;

    public UserApi find(Integer id) {
        Optional<UserApi> userApi = userApiRepository.findById(id);

        if (userApi.isPresent())
            return userApi.get();

        throw new ObjectNotFoundException(id, UserApi.class.getName());
    }

    public Page<UserApi> findAll(Pageable pageInfo) {
        return userApiRepository.findAll(pageInfo);
    }

    public UserApi save(UserApi userApi) {
        return userApiRepository.save(userApi);
    }

    public UserApi update(UserApi userApi) {
        find(userApi.getId());
        return userApiRepository.save(userApi);
    }

    public void delete(Integer id) {
        find(id);
        userApiRepository.deleteById(id);
    }
}
