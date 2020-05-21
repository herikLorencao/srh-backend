package com.srh.api.repository;

import com.srh.api.model.Recommender;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecommenderRepository extends PagingAndSortingRepository<Recommender, Integer> {
}
