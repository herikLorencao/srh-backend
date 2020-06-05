package com.srh.api.repository;

import com.srh.api.model.ItemRating;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RatingRepository extends PagingAndSortingRepository<ItemRating, Integer> {
}
