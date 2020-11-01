package com.epam.registration.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidatorImpl.class)
public @interface AgeValidation {
    String message() default "The user is under the minimal age";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}