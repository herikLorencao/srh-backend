package com.srh.api.repository;

import com.srh.api.model.Algorithm;
import com.srh.api.model.Recommendation;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecommendationRepository extends PagingAndSortingRepository<Recommendation, Integer> {
    List<Recommendation> findByAlgorithm(Algorithm algorithm);
    List<Recommendation> findByMatrixId(Integer matrixId);
    List<Recommendation> findByItem(Integer itemId);
}
