package com.nordgeo.validation.validator;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    public void initialize(ValidPassword constraint) {
    }

    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new DigitCharacterRule(1)
//                      new UppercaseCharacterRule(1),
//                      new NumericalSequenceRule(3,false),
//                      new AlphabeticalSequenceRule(3,false),
//                      new QwertySequenceRule(3,false),
//                      new WhitespaceRule()
        )
        );

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
//        List<String> messages = validator.getMessages(result);
//        String messageTemplate = String.join(",", messages);
        String messageTemplate = "Hasło musi składać się z 8-30 znaków oraz musi zawierać przynajmniej jedną cyfrę";
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
