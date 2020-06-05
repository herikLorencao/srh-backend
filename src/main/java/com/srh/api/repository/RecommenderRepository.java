package com.srh.api.repository;

import com.srh.api.model.Evaluator;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecommenderRepository extends PagingAndSortingRepository<Evaluator, Integer> {
}
