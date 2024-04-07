package az.code.trammanagementsystem.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ManufactureYearValidator.class)
@Documented
public @interface ValidManufactureYear {
    String message() default "Invalid manufacture year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

