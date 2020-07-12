package com.srh.api.repository;

import com.srh.api.model.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminRepository extends PagingAndSortingRepository<Admin, Integer> {
}
