package com.nordgeo.service.Role;

import com.nordgeo.entity.Role;
import com.nordgeo.entity.User;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.repository.RoleRepository;
import com.nordgeo.security.AuthManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    private AuthManager authManager;

    public RoleServiceImpl(RoleRepository rolerepository, AuthManager authManager) {
        this.roleRepository = rolerepository;
        this.authManager = authManager;
    }


    @Override
    public Role findById(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> findAll(PageRequest page) {
        return roleRepository.findAll(page);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void delete(int id) {
        User authUser = authManager.user();

        if (!authUser.getRole().getName().matches("Admin"))
            throw new AdminOperationNotAllowedException(null);
        else {
            roleRepository.delete(id);
        }
    }

}
