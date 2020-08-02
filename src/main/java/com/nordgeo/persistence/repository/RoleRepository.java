package com.nordgeo.persistence.repository;


import com.nordgeo.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

    @Override
    Iterable<Role> findAll();
}