package com.srh.api.repository;

import com.srh.api.model.Rating;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RatingRepository extends PagingAndSortingRepository<Rating, Integer> {
}
