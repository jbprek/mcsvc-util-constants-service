package org.bagab.mcsvc.util.constants.countrycode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class CountryCodeFindKeyValidator implements ConstraintValidator<CountryCodeFindKey, String> {
    public static final Set<String> validFindKeys = Set.of("alpha2", "alpha3", "name");


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        if (value == null || value.isBlank())
            isValid = false;
        if ( !validFindKeys.contains(value))
            isValid = false;

        return isValid;
    }
}
