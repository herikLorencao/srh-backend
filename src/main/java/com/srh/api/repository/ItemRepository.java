package com.srh.api.repository;

import com.srh.api.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {
}
