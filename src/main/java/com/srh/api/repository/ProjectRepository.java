package com.srh.api.repository;

import com.srh.api.model.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer> {
}
