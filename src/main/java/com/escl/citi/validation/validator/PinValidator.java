package com.escl.citi.validation.validator;


import org.passay.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PinValidator {

    public boolean validate(String password) {
        boolean matchNumbers = password.matches("^[0-9]{5}$");

        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new NumericalSequenceRule(3, false),
                new RepeatCharacterRegexRule(3))
        );

        RuleResult ruleResult = validator.validate(new PasswordData(password));

        return matchNumbers && ruleResult.isValid();

    }
}
