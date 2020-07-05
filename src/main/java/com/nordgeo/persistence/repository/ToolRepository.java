package com.nordgeo.persistence.repository;

import com.nordgeo.entity.Tool;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends PagingAndSortingRepository<Tool, Integer> {

    @Override
    Iterable<Tool> findAll();
}
