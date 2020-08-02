package com.nordgeo.persistence.repository;

import com.nordgeo.entity.Tool;
import com.nordgeo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends PagingAndSortingRepository<Tool,Integer> {

    @Override
    Iterable<Tool> findAll();

    Page<Tool> findToolsByAvailableIsTrue(Pageable page);

    Page<Tool> findToolsByAvailableIsFalse(Pageable page);

    Page<Tool> findToolsByUser(User user, Pageable page);
}
