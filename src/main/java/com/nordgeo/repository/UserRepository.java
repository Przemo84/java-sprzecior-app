package com.nordgeo.repository;


import com.nordgeo.entity.Role;
import com.nordgeo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

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

    Page<User> findUsersByRoleIsNotInAndLockDateIsNull(List<Role> roles, Pageable page);


}
