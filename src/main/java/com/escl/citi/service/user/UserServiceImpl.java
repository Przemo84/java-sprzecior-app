package com.escl.citi.service.user;

import com.escl.citi.data.UserPasswordDto;
import com.escl.citi.data.dto.DealerDto;
import com.escl.citi.data.mapper.DealerDtoMapper;
import com.escl.citi.entity.Role;
import com.escl.citi.entity.User;
import com.escl.citi.exception.*;
import com.escl.citi.persistence.repository.RoleRepository;
import com.escl.citi.persistence.repository.UserRepository;
import com.escl.citi.security.AuthManager;
import com.escl.citi.service.Role.RoleService;
import com.escl.citi.service.user.activity.UserActivitiesService;
import com.escl.citi.storage.CroppedImageParams;
import com.escl.citi.storage.StorageException;
import com.escl.citi.storage.StorageService;
import com.escl.citi.utils.Sanitizer;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private PasswordEncoder passwordEncoder;

    private AuthManager authManager;

    private StorageService storageService;

    private UserActivitiesService userActivitiesService;

    private DealerDtoMapper dealerDtoMapper;

    private static Role admin;
    private static Role dealer;
    private static Role pinSupervisor;

    public UserServiceImpl(UserRepository repository, @Lazy PasswordEncoder passwordEncoder, AuthManager authManager,
                           RoleRepository roleRepository, StorageService storageService,
                           UserActivitiesService userActivitiesService, DealerDtoMapper dealerDtoMapper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.storageService = storageService;
        this.userActivitiesService = userActivitiesService;
        this.dealerDtoMapper = dealerDtoMapper;

        admin = roleRepository.findOne(User.RoleName.ADMIN_ROLE.getValue());
        dealer = roleRepository.findOne(User.RoleName.DEALER_ROLE.getValue());
        pinSupervisor = roleRepository.findOne(User.RoleName.EMPLOYEE_ROLE.getValue());
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

        User savedUser = repository.save(sanitizeUserOrPinSupervisor(user));
        saveUserActionHistory(user, "Utworzenie");

        return savedUser;
    }

    @Override
    public void lock(Integer id) throws AdminOperationNotAllowedException, AdminNotAllowedToDeleteHimselfException {
        String authUserRole = authManager.user().getRole().getName();

        if (!authUserRole.equals("Administrator"))
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

        if (!authUserRole.equals("Administrator"))
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
    public Page<User> findAllDealers(PageRequest page) {
        return repository.findUsersByRoleAndLockDateIsNull(dealer, page);
    }

    @Override
    public List<User> findAllDealers() {
        return repository.findUsersByRoleAndLockDateIsNull(dealer);
    }

    @Override
    public Page<User> findAllAdmins(PageRequest page) {
        return repository.findUsersByRoleAndLockDateIsNull(admin, page);
    }

    @Override
    public Page<User> findAllPinSupervisors(PageRequest page) {
        return repository.findUsersByRoleAndLockDateIsNull(pinSupervisor, page);
    }

    @Override
    public Page<User> findAllUsersNotAdminsNotDealersNotPinSupervisors(PageRequest page) {
        List<Role> roles = new ArrayList<>();
        roles.add(admin);
        roles.add(dealer);
        roles.add(pinSupervisor);

        return repository.findUsersByRoleIsNotIn(roles, page);
    }

    @Override
    public void save(DealerDto dealerDto) {
        User user = dealerDtoMapper.map(dealerDto);
        user.setRole(dealer);

        MultipartFile file = dealerDto.getFile();
        CroppedImageParams imageParams = dealerDto.getImageParams();

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        if (user.getId() == null) {
            saveDealer(user, file, imageParams);
        } else {
            User existingUser = findById(dealerDto.getId());
            user.setLastLoginDate(existingUser.getLastLoginDate());
            user.setActive(existingUser.getActive());

            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            }

            updateDealer(user, file, imageParams);
        }
    }

    @Override
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void updateWithOldPassword(User user) {
        if (user.getRole().getName().equals("Dealer"))
            repository.save(sanitizeDealer(user));
        else {
            repository.save(sanitizeUserOrPinSupervisor(user));
        }

        saveUserActionHistory(user, "Edycja");

    }


    private void saveDealer(User user, MultipartFile file, CroppedImageParams croppedImageParams) {
        //no-image loaded
        if (file.isEmpty())
            throw new GenericImageException("error.image.needed");

        try {
            user.setIcon(storageService.getImageFileNameAndStore(file, croppedImageParams));
        } catch (StorageException e) {
            throw new GenericImageException("error.image.file.type");
        } catch (ImageWrongScaleException e) {
            throw new GenericImageException("error.image.scale");
        }

        user.setActive(true);

        repository.save(sanitizeDealer(user));
        userActivitiesService.saveActivity("Utworzono Dealera ID: <a href=\"/admin/dealer/form/"
                + user.getId() + "\">" + user.getId() + "</a>");
    }

    private void updateDealer(User user, MultipartFile file, CroppedImageParams croppedImageParams) {
        try {
            //if is old image on update
            if (file.isEmpty() && !croppedImageParams.getOldImageName().isEmpty()) {
                String filename = storageService.store(storageService.getExistingImageAsMultipartFile(croppedImageParams.getOldImageName()), croppedImageParams);
                user.setIcon(filename);
            }

            //new image
            if (!file.isEmpty()) {
                user.setIcon(storageService.getImageFileNameAndStore(file, croppedImageParams));
            }
        } catch (StorageException e) {
            throw new GenericImageException("error.image.file.type");
        } catch (ImageWrongScaleException e) {
            throw new GenericImageException("error.image.scale");
        }

        repository.save(sanitizeDealer(user));
        userActivitiesService.saveActivity("Edycja Dealera ID: <a href=\"/admin/dealer/form/"
                + user.getId() + "\">" + user.getId() + "</a>");
    }

    private void saveUserActionHistory(User user, String action) {

        switch (user.getRole().getName()) {
            case "Administrator": {
                userActivitiesService.saveActivity(action + " Administratora ID: <a href=\"/admin/admins/form/"
                        + user.getId() + "\">" + user.getId() + "</a>");
                break;
            }
            case "Dealer": {
                userActivitiesService.saveActivity(action + " Dealera ID: <a href=\"/admin/dealer/form/"
                        + user.getId() + "\">" + user.getId() + "</a>");
                break;
            }
            case "Pracownik": {
                userActivitiesService.saveActivity(action + " Zarządcy Pin ID: <a href=\"/admin/pin-supervisor/form/"
                        + user.getId() + "\">" + user.getId() + "</a>");
                break;
            }
        }

    }

    private User sanitizeUserOrPinSupervisor(User user) {
        user.setEmail(Sanitizer.text(user.getEmail()));
        user.setUsername(Sanitizer.text(user.getUsername()));
        user.setFirstName(Sanitizer.text(user.getFirstName()));
        user.setLastName(Sanitizer.text(user.getLastName()));

        return user;
    }

    private User sanitizeDealer(User user) {
        user.setFirstName(Sanitizer.text(user.getFirstName()));
        user.setLastName(Sanitizer.text(user.getLastName()));
        user.setEmail(Sanitizer.text(user.getEmail()));
        user.setUsername(Sanitizer.text(user.getUsername()));
        user.setMobile(Sanitizer.text(user.getMobile()));

        return user;
    }
}
