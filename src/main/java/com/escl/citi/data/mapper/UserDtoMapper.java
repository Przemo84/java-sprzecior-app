package com.escl.citi.data.mapper;

import com.escl.citi.data.dto.UserDto;
import com.escl.citi.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserDtoMapper {

    public UserDto map(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setPasswordConfirm(user.getPasswordConfirm());

        return dto;

    }

    public User map(UserDto dto){
        User user = new User();

        if (dto.getId() != null)
            user.setId(dto.getId());

        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setPasswordConfirm(dto.getPasswordConfirm());

        return user;
    }
}
