package com.nordgeo.service.user;

import com.nordgeo.data.dto.UserDto;
import com.nordgeo.data.dto.UserPasswordDto;
import com.nordgeo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;



public interface UserService {
    User findByUsername(String username);

    User findByEmail(String email);

    User findById(Integer id);

    Page<User> findAll(PageRequest pageRequest);

    User save(User user);

    void lock(Integer id);

    void changePassword(UserPasswordDto userPasswordDto);

    Page<User> findAllLocked(PageRequest pageRequest);

    void unlock(int id);

    Page<User> findAllAdmins(PageRequest page);

    Page<User> findAllEmployees(PageRequest page);

    void updateWithOldPassword(User user);

    void setLastLoginDate(User user);

    Page<User> findAllEditors(PageRequest page);

    void changeRole(int id, User.RoleName roleName);

    void saveOrUpdate(UserDto userDto, User.RoleName roleName);
}
