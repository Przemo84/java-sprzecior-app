package com.escl.citi.persistence.repository;

import com.escl.citi.entity.Role;
import com.escl.citi.entity.Tool;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends PagingAndSortingRepository<Tool, Integer> {

    @Override
    Iterable<Tool> findAll();
}
