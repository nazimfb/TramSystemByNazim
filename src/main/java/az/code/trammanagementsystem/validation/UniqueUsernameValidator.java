package az.code.trammanagementsystem.validation;

import az.code.trammanagementsystem.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Username cannot be null or empty")
                    .addConstraintViolation();
            return false;
        }
        if (value.length() < 3) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Username must be at least 3 characters long")
                    .addConstraintViolation();
            return false;
        }
        return !userService.usernameExists(value);
    }
}
