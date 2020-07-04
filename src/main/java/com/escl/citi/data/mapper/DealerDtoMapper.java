package com.escl.citi.data.mapper;

import com.escl.citi.data.dto.DealerDto;
import com.escl.citi.entity.User;
import com.escl.citi.storage.CroppedImageParams;
import org.springframework.stereotype.Service;

@Service
public class DealerDtoMapper {

    public DealerDto map(User user) {
        DealerDto dto = new DealerDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setPasswordConfirm(user.getPasswordConfirm());
        dto.setMobile(user.getMobile());
        dto.setIcon(user.getIcon());

        CroppedImageParams imageParams = new CroppedImageParams();
        imageParams.setOldImageName(user.getIcon());

        dto.setImageParams(imageParams);

        return dto;

    }

    public User map(DealerDto dto) {
        User user = new User();

        if (dto.getId() != null)
            user.setId(dto.getId());

        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setPasswordConfirm(dto.getPasswordConfirm());
        user.setMobile(dto.getMobile());
        user.setIcon(dto.getIcon());

        return user;
    }
}
