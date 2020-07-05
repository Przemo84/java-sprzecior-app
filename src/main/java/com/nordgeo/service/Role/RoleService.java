package com.nordgeo.service.Role;

import com.nordgeo.entity.Role;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RoleService {

    Role findById(Integer id);

    Iterable<Role> findAll();

    Page<Role> findAll(PageRequest page);

    void save(Role role);

    void delete(int id) throws AdminOperationNotAllowedException;
}
