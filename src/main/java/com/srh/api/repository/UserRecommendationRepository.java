package com.srh.api.repository;

import com.srh.api.model.UserRecommendation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRecommendationRepository extends PagingAndSortingRepository<UserRecommendation, Integer> {
}
