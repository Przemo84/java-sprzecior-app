package com.escl.citi.validation.validator;

import com.escl.citi.data.UserPasswordDto;
import com.escl.citi.entity.User;
import com.escl.citi.security.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserPasswordValidator implements Validator {

    @Autowired
    private AuthManager authManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    //TODO - jeszcze validacja na obowiązkowe znaki i cyferki
    @Override
    public void validate(Object o, Errors errors) {
        UserPasswordDto userPasswordDto = (UserPasswordDto) o;
        User authUser = authManager.user();

        String current = authUser.getPassword();
        boolean match = passwordEncoder.matches(userPasswordDto.getCurrentPassword(), current);

        if (!match)
            errors.rejectValue("currentPassword", "old.password.not.match");

        if (!userPasswordDto.getNewPassword().isEmpty() && userPasswordDto.getNewPassword().length() < 6)
            errors.rejectValue("newPassword", "Size.userForm.password");

        if (!userPasswordDto.getNewPassword().isEmpty() && !userPasswordDto.getNewPassword().equals(userPasswordDto.getPasswordConfirm())) {
            errors.rejectValue("newPassword", "password.equal", "Hasła nie są Identyczne");
            errors.rejectValue("passwordConfirm", "password.equal", "Hasła nie są Identyczne");
        }

        if (userPasswordDto.getNewPassword().isEmpty())
            errors.rejectValue("newPassword", "password.empty");

    }

}