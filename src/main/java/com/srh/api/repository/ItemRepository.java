package com.srh.api.repository;

import com.srh.api.model.Item;
import com.srh.api.model.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {
    List<Item> findByProject(Project project);
}
