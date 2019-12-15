package org.bagab.mcsvc.util.constants.countrycode;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = CountryCodeFindKeyValidator.class)
@Documented
public @interface CountryCodeFindKey {
}
