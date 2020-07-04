package com.escl.citi.service.Role;

import com.escl.citi.entity.Role;
import com.escl.citi.entity.User;
import com.escl.citi.exception.AdminOperationNotAllowedException;
import com.escl.citi.persistence.repository.RoleRepository;
import com.escl.citi.persistence.repository.UserRepository;
import com.escl.citi.security.AuthManager;
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

        if (!authUser.getRole().getName().matches("Administrator"))
            throw new AdminOperationNotAllowedException(null);
        else {
            roleRepository.delete(id);
        }
    }

}
