package com.escl.citi.validation.validator;


import org.springframework.stereotype.Service;

@Service
public class MobileAccountPasswordValidator {

    public boolean validate(String password) {
        boolean matchNumbers = password.matches("^[0-9]{8}$");

//        passay
//        PasswordValidator validator = new PasswordValidator(Arrays.asList(
//                new NumericalSequenceRule(4, false),
//                new RepeatCharacterRegexRule(4))
//        );
//
//        RuleResult ruleResult = validator.validate(new PasswordData(password));
//
//        return matchNumbers && ruleResult.isValid();

        return true;
    }
}
