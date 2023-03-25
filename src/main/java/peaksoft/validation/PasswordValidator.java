package peaksoft.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 23.03.2023
 */
public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password.length() > 4;
    }
}
