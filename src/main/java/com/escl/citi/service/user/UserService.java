package com.escl.citi.service.user;

import com.escl.citi.data.UserPasswordDto;
import com.escl.citi.data.dto.DealerDto;
import com.escl.citi.entity.User;
import com.escl.citi.exception.UserLastSixPasswordException;
import com.escl.citi.storage.CroppedImageParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User findByUsername(String username);

    User findByEmail(String email);

    User findById(Integer id);

    Page<User> findAll(PageRequest pageRequest);

    User save(User user);

    void lock(Integer id);

    void changePassword(UserPasswordDto userPasswordDto) throws UserLastSixPasswordException;

    Page<User> findAllLocked(PageRequest pageRequest);

    void unlock(int id);

    void massAction(Integer[] ids, String action);

    Page<User> findAllDealers(PageRequest page);

    List<User> findAllDealers();

    Page<User> findAllAdmins(PageRequest page);

    Page<User> findAllPinSupervisors(PageRequest page);

    Page<User> findAllUsersNotAdminsNotDealersNotPinSupervisors(PageRequest page);

    void save(DealerDto dealerDto) throws IOException;

    Iterable<User> findAll();

    void updateWithOldPassword(User user);
}
