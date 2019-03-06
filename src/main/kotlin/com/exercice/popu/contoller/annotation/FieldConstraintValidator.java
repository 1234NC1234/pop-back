package com.exercice.popu.contoller.annotation;

import com.exercice.popu.model.in.FieldsEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class FieldConstraintValidator implements ConstraintValidator<FieldConstraint, Map<String, String>> {

    @Override
    public void initialize(FieldConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Map<String, String> stringStringMap, ConstraintValidatorContext constraintValidatorContext) {
        if (stringStringMap != null) {
            Set<String> fieldSet = new HashSet<String>();
            Arrays.asList(FieldsEnum.values()).forEach(x -> fieldSet.add(x.getValue()));
            if (!fieldSet.containsAll(stringStringMap.keySet())) return false;
        }
        return true;
    }
}
