package com.escl.citi.persistence.repository;


import com.escl.citi.entity.Role;
import com.escl.citi.entity.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    Page<User> findAllByLockDateIsNull(Pageable pageable);

    User findByEmail(String email);

    User findByUsername(String username);

    User findById(Integer id);

    Page<User> findAllByLockDateIsNotNull(Pageable pageable);


    Page<User> findUsersByRoleAndLockDateIsNull(Role role, Pageable page);

    List<User> findUsersByRoleAndLockDateIsNull(Role role);

    Page<User> findUsersByRoleIsNotIn(List<Role> roles, Pageable page);

}