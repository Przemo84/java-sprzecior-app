package com.escl.citi.seeders;

import com.escl.citi.entity.Role;
import com.escl.citi.entity.User;
import com.escl.citi.persistence.repository.RoleRepository;
import com.escl.citi.persistence.repository.UserRepository;
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
        Role developerRole = roleRepository.findTopByName("Developer");
        Role dealerRole = roleRepository.findOne(2);
        Role employeeRole = roleRepository.findOne(3);

        roleRepository.save(adminRole);
        roleRepository.save(dealerRole);
        roleRepository.save(employeeRole);

        User user = new User();
        user.setEmail("foo@bar.com");
        user.setUsername("foo");
        user.setFirstName("foo");
        user.setLastName("foo");
        user.setMustChangePassword(false);
        user.setPassword(passwordEncoder.encode("random"));
        user.setRole(adminRole);

        User dealer = new User();
        dealer.setEmail("dealer@bar.com");
        dealer.setUsername("dealer");
        dealer.setFirstName("dealer");
        dealer.setLastName("dealer");
        dealer.setMustChangePassword(false);
        dealer.setPassword(passwordEncoder.encode("random"));
        dealer.setRole(dealerRole);

        User employee = new User();
        employee.setEmail("employee@bar.com");
        employee.setUsername("employee");
        employee.setFirstName("employee");
        employee.setLastName("employee");
        employee.setMustChangePassword(false);
        employee.setPassword(passwordEncoder.encode("random"));
        employee.setRole(employeeRole);

        try{
            User foo = userRepository.save(user);
            User bar = userRepository.save(dealer);
            userRepository.save(employee);

        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
        }

        try{
            User bar = userRepository.findByUsername("dealer");
            userRepository.save(employee);

        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
        }

        try{
            User developer = new User();
            developer.setEmail("piotr.theis@etendard.pl");
            developer.setUsername("piotr.theis");
            developer.setFirstName("piotr");
            developer.setLastName("theis");
            developer.setMustChangePassword(false);
            developer.setPassword(passwordEncoder.encode("random"));
            developer.setRole(developerRole);
            userRepository.save(developer);

        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
        }
    }
}
