package com.exercice.popu.contoller.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldConstraintValidator.class)
public @interface FieldConstraint {
    String message() default "Field doesn't exists";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}