package com.nordgeo.service.user;

import com.nordgeo.data.UserPasswordDto;
import com.nordgeo.entity.Role;
import com.nordgeo.entity.User;
import com.nordgeo.exception.AdminNotAllowedToDeleteHimselfException;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.exception.UserLastSixPasswordException;
import com.nordgeo.persistence.repository.RoleRepository;
import com.nordgeo.persistence.repository.UserRepository;
import com.nordgeo.security.AuthManager;
import com.nordgeo.service.user.activity.UserActivitiesService;
import com.nordgeo.storage.StorageService;
import com.nordgeo.utils.Sanitizer;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private PasswordEncoder passwordEncoder;

    private AuthManager authManager;

    private StorageService storageService;

    private UserActivitiesService userActivitiesService;


    private static Role admin;
    private static Role employee;

    public UserServiceImpl(UserRepository repository, @Lazy PasswordEncoder passwordEncoder, AuthManager authManager,
                           RoleRepository roleRepository, StorageService storageService,
                           UserActivitiesService userActivitiesService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.storageService = storageService;
        this.userActivitiesService = userActivitiesService;

        admin = roleRepository.findOne(User.RoleName.ADMIN_ROLE.getValue());
        employee = roleRepository.findOne(User.RoleName.EMPLOYEE_ROLE.getValue());
    }


    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User findById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        return repository.findAllByLockDateIsNull(pageRequest);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = repository.save(sanitizeUser(user));
        saveUserActionHistory(user, "Utworzenie");

        return savedUser;
    }

    @Override
    public void lock(Integer id) throws AdminOperationNotAllowedException, AdminNotAllowedToDeleteHimselfException {
        String authUserRole = authManager.user().getRole().getName();

        if (!authUserRole.equals("Admin"))
            throw new AdminOperationNotAllowedException(null);
        if (authManager.user().getId().equals(id))
            throw new AdminNotAllowedToDeleteHimselfException();
        else {
            User user = repository.findById(id);
            user.setLockDate(new Date());
            user.setActive(false);

            repository.save(user);
            saveUserActionHistory(user, "Zablokowanie");
        }
    }

    @Override
    public void unlock(int id) throws AdminOperationNotAllowedException, AdminNotAllowedToDeleteHimselfException {
        String authUserRole = authManager.user().getRole().getName();

        if (!authUserRole.equals("Admin"))
            throw new AdminOperationNotAllowedException(null);
        if (authManager.user().getId().equals(id))
            throw new AdminNotAllowedToDeleteHimselfException();
        else {
            User user = repository.findById(id);
            user.setLockDate(null);
            user.setActive(true);
            user.setLoginAttempts(0);

            repository.save(user);

            saveUserActionHistory(user, "Odblokowanie");

        }
    }

    @Override
    public void massAction(Integer[] ids, String action) {
        switch (action) {
            case "lock": {
                for (Integer id : ids) {
                    lock(id);
                }
                break;
            }
            case "": {
                break;
            }

        }
    }

    @Override
    public void changePassword(UserPasswordDto userPasswordDto) throws UserLastSixPasswordException {
        User user = authManager.user();
        String password = passwordEncoder.encode(userPasswordDto.getNewPassword());

        for (String userPastPassword : user.getUserPasswords()) {
            if (passwordEncoder.matches(userPasswordDto.getNewPassword(), userPastPassword))
                throw new UserLastSixPasswordException();
        }


        user.setPassword(password);
        user.addUserPassword(password);
        user.setMustChangePassword(false);

        repository.save(user);
        saveUserActionHistory(user, "Zmiana hasła");
    }

    @Override
    public Page<User> findAllLocked(PageRequest pageRequest) {
        return repository.findAllByLockDateIsNotNull(pageRequest);
    }

    @Override
    public Page<User> findAllAdmins(PageRequest page) {
        return repository.findUsersByRoleAndLockDateIsNull(admin, page);
    }

    @Override
    public Page<User> findAllEmployees(PageRequest page) {
        return repository.findUsersByRoleAndLockDateIsNull(employee, page);
    }

    @Override
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void updateWithOldPassword(User user) {
        repository.save(sanitizeUser(user));

        saveUserActionHistory(user, "Edycja");
    }

    @Override
    public void setLastLoginDate(User user) {
        user.setLastLoginDate(new Date());
        repository.save(user);
    }


    private void saveUserActionHistory(User user, String action) {

        switch (user.getRole().getName()) {
            case "Admin": {
                userActivitiesService.saveActivity(action + " Administratora ID: <a href=\"/admin/admins/form/"
                        + user.getId() + "\">" + user.getId() + "</a>");
                break;
            }

            case "Employee": {
                userActivitiesService.saveActivity(action + " Pracownika ID: <a href=\"/admin/employees/form/"
                        + user.getId() + "\">" + user.getId() + "</a>");
                break;
            }

            case "Editor": {
                userActivitiesService.saveActivity(action + " Pracownika ID: <a href=\"/admin/employees/form/"
                        + user.getId() + "\">" + user.getId() + "</a>");
                break;
            }
        }

    }

    private User sanitizeUser(User user) {
        user.setEmail(Sanitizer.text(user.getEmail()));
        user.setUsername(Sanitizer.text(user.getUsername()));
        user.setFirstName(Sanitizer.text(user.getFirstName()));
        user.setLastName(Sanitizer.text(user.getLastName()));

        return user;
    }

}
