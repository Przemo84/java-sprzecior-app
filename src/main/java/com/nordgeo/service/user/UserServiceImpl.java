package com.nordgeo.service.user;

import com.nordgeo.data.dto.UserDto;
import com.nordgeo.data.dto.UserPasswordDto;
import com.nordgeo.data.mapper.UserDtoMapper;
import com.nordgeo.entity.Role;
import com.nordgeo.entity.User;
import com.nordgeo.exception.*;
import com.nordgeo.repository.RoleRepository;
import com.nordgeo.repository.UserRepository;
import com.nordgeo.security.AuthManager;
import com.nordgeo.service.user.activity.UserActivitiesService;
import com.nordgeo.utils.Sanitizer;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final AuthManager authManager;

    private final UserActivitiesService userActivitiesService;

    private final RoleRepository roleRepository;

    private final UserDtoMapper userDtoMapper;


    public UserServiceImpl(UserRepository repository, @Lazy PasswordEncoder passwordEncoder, AuthManager authManager,
                           RoleRepository roleRepository,
                           UserActivitiesService userActivitiesService, UserDtoMapper userDtoMapper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.userActivitiesService = userActivitiesService;
        this.roleRepository = roleRepository;
        this.userDtoMapper = userDtoMapper;
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

        User user = repository.findOne(id);

        if (user == null)
            throw new ItemNotFoundException();

        return user;
    }

    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        return repository.findAllByLockDateIsNull(pageRequest);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        String action = "Utworzenie";

        if (user.getId() != null)
            action = "Edycja";

        User savedUser = repository.save(sanitizeUser(user));

        saveUserActionHistory(user, action);

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
    public void changePassword(UserPasswordDto userPasswordDto) {
        User user = authManager.user();
        String password = passwordEncoder.encode(userPasswordDto.getNewPassword());

        user.setPassword(password);
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
        return repository.findUsersByRoleAndLockDateIsNull(roleRepository.findOne(User.RoleName.ADMIN_ROLE.getValue()), page);
    }

    @Override
    public Page<User> findAllEditors(PageRequest page) {
        return repository.findUsersByRoleAndLockDateIsNull(roleRepository.findOne(User.RoleName.EDITOR_ROLE.getValue()), page);
    }


    @Override
    public Page<User> findAllEmployees(PageRequest page) {
        List<Role> roles = new ArrayList<Role>() {{
            add(roleRepository.findOne(User.RoleName.ADMIN_ROLE.getValue()));
        }};

        return repository.findUsersByRoleIsNotInAndLockDateIsNull(roles, page);
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

        if (user.getRole().getName().equals("Admin")) {
            userActivitiesService.saveActivity(authManager.user(), action + " Administratora: " + user.getFullName() + ", ID: <a href=\"/admin/admins/form/"
                    + user.getId() + "\">" + user.getId() + "</a>");
        } else {
            userActivitiesService.saveActivity(authManager.user(), action + " Użytkownika: " + user.getFullName() + ", ID: <a href=\"/admin/employees/form/"
                    + user.getId() + "\">" + user.getId() + "</a>");
        }

    }

    @Override
    public void changeRole(int id, User.RoleName roleName) {
        User user = repository.findById(id);
        Role role = roleRepository.findOne(roleName.getValue());
        user.setRole(role);
        repository.save(user);

        String changedRoleName = "Edytor";

        if (user.getRole().getName().equals("Employee"))
            changedRoleName = "Pracownik";

        saveUserActionHistory(user, "Zmiana roli na: " + changedRoleName + " dla ");
    }

    @Override
    public void saveOrUpdate(UserDto userDto, User.RoleName roleName) {

        User user = userDtoMapper.map(userDto);
        user.setRole(roleRepository.findOne(roleName.getValue()));

        if (userDto.getId() != null && userDto.getPassword().isEmpty()) {
            user.setPassword(repository.findById(userDto.getId()).getPassword());
            updateWithOldPassword(user);
        } else {
            save(user);
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
