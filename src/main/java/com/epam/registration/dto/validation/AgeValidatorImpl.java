package com.epam.registration.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeValidatorImpl implements ConstraintValidator<AgeValidation, LocalDate> {

    private static final long MIN_AGE = 18;

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate today = LocalDate.now();
        long years = ChronoUnit.YEARS.between(localDate, today);
        return years > MIN_AGE;
    }
}
