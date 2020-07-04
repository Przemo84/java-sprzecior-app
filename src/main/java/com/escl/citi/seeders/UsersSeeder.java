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
        Role pinSupervisorRole = roleRepository.findOne(3);

        roleRepository.save(adminRole);
        roleRepository.save(dealerRole);
        roleRepository.save(pinSupervisorRole);

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

        User pinSupervisor = new User();
        pinSupervisor.setEmail("pin@bar.com");
        pinSupervisor.setUsername("pin");
        pinSupervisor.setFirstName("pin");
        pinSupervisor.setLastName("pin");
        pinSupervisor.setMustChangePassword(false);
        pinSupervisor.setPassword(passwordEncoder.encode("random"));
        pinSupervisor.setRole(pinSupervisorRole);

        User test1 = new User();
        test1.setEmail("sc64720@imcnam.ssmb.com");
        test1.setUsername("sc64720");
        test1.setFirstName("sc64720");
        test1.setLastName("sc64720");
        test1.setMustChangePassword(false);
        test1.setPassword(passwordEncoder.encode("random"));
        test1.setRole(adminRole);

        User test2 = new User();
        test2.setEmail("ap96185@imcnam.ssmb.com");
        test2.setUsername("ap96185");
        test2.setFirstName("ap96185");
        test2.setLastName("ap96185");
        test2.setMustChangePassword(false);
        test2.setPassword(passwordEncoder.encode("random"));
        test2.setRole(adminRole);

        User test3 = new User();
        test3.setEmail("cz99689@imcnam.ssmb.com");
        test3.setUsername("cz99689");
        test3.setFirstName("cz99689");
        test3.setLastName("cz99689");
        test3.setMustChangePassword(false);
        test3.setPassword(passwordEncoder.encode("random"));
        test3.setRole(adminRole);

        User test4 = new User();
        test4.setEmail("dt84571@imcnam.ssmb.com");
        test4.setUsername("dt84571");
        test4.setFirstName("dt84571");
        test4.setLastName("dt84571");
        test4.setMustChangePassword(false);
        test4.setPassword(passwordEncoder.encode("random"));
        test4.setRole(adminRole);

        try{
            User foo = userRepository.save(user);
            User bar = userRepository.save(dealer);
            userRepository.save(pinSupervisor);

            foo.setChecker(bar);
        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
        }

        try{
            User bar = userRepository.findByUsername("dealer");
            User test1User = userRepository.save(test1);
            User test2User = userRepository.save(test2);
            User test3User = userRepository.save(test3);
            User test4User = userRepository.save(test4);
            userRepository.save(pinSupervisor);

            test1User.setChecker(bar);
            test2User.setChecker(bar);
            test3User.setChecker(bar);
            test4User.setChecker(bar);
            userRepository.save(user);
            userRepository.save(test1User);
            userRepository.save(test2User);
            userRepository.save(test3User);
            userRepository.save(test4User);
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
