package com.srh.api.repository;

import com.srh.api.model.Recommendation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecommendationRepository extends PagingAndSortingRepository<Recommendation, Integer> {
}
