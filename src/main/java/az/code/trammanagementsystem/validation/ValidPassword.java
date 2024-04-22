package az.code.trammanagementsystem.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface ValidPassword {
    String message() default "Password must be at least 8 characters long and contain at least one digit, one uppercase letter, and one lowercase letter";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
