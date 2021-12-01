package project.validator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Documented;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

import project.validator.algorithms.TaxIdentificatorNumberIsaDataBaseConstraint;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.ANNOTATION_TYPE,
	ElementType.FIELD,
	ElementType.PARAMETER,
	ElementType.METHOD})
@Constraint(validatedBy = TaxIdentificatorNumberIsaDataBaseConstraint.class)
public @interface TaxIdentificatorNumberIsaDataBase {

	String message() default "{project.validator.annotation.TaxIdentificatorNumberIsaDataBase.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
