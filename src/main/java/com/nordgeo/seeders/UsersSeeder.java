package com.nordgeo.seeders;

import com.nordgeo.entity.Role;
import com.nordgeo.entity.User;
import com.nordgeo.persistence.repository.RoleRepository;
import com.nordgeo.persistence.repository.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsersSeeder {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UsersSeeder(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener()
    @Order(1)
    public void appReady(ContextRefreshedEvent event) {
        Role adminRole = roleRepository.findOne(1);
        Role employeeRole = roleRepository.findOne(2);

        roleRepository.save(adminRole);
        roleRepository.save(employeeRole);

        User user = new User();
        user.setEmail("mike@bar.com");
        user.setUsername("mike");
        user.setFirstName("Michał");
        user.setLastName("Kmiecik");
        user.setMustChangePassword(false);
        user.setPassword(passwordEncoder.encode("random"));
        user.setRole(adminRole);

        User employee = new User();
        employee.setEmail("employee@bar.com");
        employee.setUsername("employee");
        employee.setFirstName("employee");
        employee.setLastName("employee");
        employee.setMustChangePassword(false);
        employee.setPassword(passwordEncoder.encode("random"));
        employee.setRole(employeeRole);

        try{
            userRepository.save(user);
            userRepository.save(employee);

        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
        }

    }
}
