package az.code.trammanagementsystem.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.util.Date;

public class ManufactureYearValidator implements ConstraintValidator<ValidManufactureYear, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value >= 2010 && value <= LocalDate.now().getYear();
    }
}
