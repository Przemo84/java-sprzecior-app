package com.nordgeo.seeders;

import com.nordgeo.entity.Role;
import com.nordgeo.entity.User;
import com.nordgeo.repository.RoleRepository;
import com.nordgeo.repository.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserSeeder(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener()
    @Order(1)
    public void appReady(ContextRefreshedEvent event) {
        Role adminRole = new Role("Admin");
        Role editorRole = new Role("Editor");
        Role employeeRole = new Role("Employee");

        roleRepository.save(adminRole);
        roleRepository.save(editorRole);
        roleRepository.save(employeeRole);

        User admin = new User();
        admin.setEmail("admin@bar.com");
        admin.setUsername("admin");
        admin.setFirstName("Mike");
        admin.setLastName("Mike");
        admin.setMustChangePassword(false);
        admin.setPassword(passwordEncoder.encode("random"));
        admin.setRole(adminRole);

        User editor = new User();
        editor.setEmail("editor@bar.com");
        editor.setUsername("editor");
        editor.setFirstName("Marta");
        editor.setLastName("Marta");
        editor.setMustChangePassword(false);
        editor.setPassword(passwordEncoder.encode("random"));
        editor.setRole(editorRole);

        User employee = new User();
        employee.setEmail("employee@bar.com");
        employee.setUsername("employee");
        employee.setFirstName("employee");
        employee.setLastName("employee");
        employee.setMustChangePassword(false);
        employee.setPassword(passwordEncoder.encode("random"));
        employee.setRole(employeeRole);

        try{
            userRepository.save(admin);
            userRepository.save(editor);
            userRepository.save(employee);

        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
        }

    }
}
