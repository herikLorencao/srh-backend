package com.srh.api.repository;

import com.srh.api.model.Item;
import com.srh.api.model.ItemRating;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ItemRatingRepository extends PagingAndSortingRepository<ItemRating, Integer> {
    List<ItemRating> findByItem(Item item);
}
