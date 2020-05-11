package com.srh.api.service;

import com.srh.api.model.UserRecommendation;
import com.srh.api.repository.UserRecommendationRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRecommendationService {
    @Autowired
    private UserRecommendationRepository userRecommendationRepository;

    public UserRecommendation find(Integer id) {
        Optional<UserRecommendation> userRecommendation = userRecommendationRepository.findById(id);

        if (userRecommendation.isPresent())
            return userRecommendation.get();

        throw new ObjectNotFoundException(id, UserRecommendation.class.getName());
    }

    public Page<UserRecommendation> findAll(Pageable pageInfo) {
        return userRecommendationRepository.findAll(pageInfo);
    }

    public UserRecommendation save(UserRecommendation userRecommendation) {
        return userRecommendationRepository.save(userRecommendation);
    }

    public UserRecommendation update(UserRecommendation userRecommendation) {
        find(userRecommendation.getId());
        return userRecommendationRepository.save(userRecommendation);
    }

    public void delete(Integer id) {
        find(id);
        userRecommendationRepository.deleteById(id);
    }
}
