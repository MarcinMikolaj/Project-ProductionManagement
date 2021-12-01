package project.validator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Documented;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

import project.validator.algorithms.OrderIdentificatorConstraint;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.FIELD,
	ElementType.ANNOTATION_TYPE,
	ElementType.PARAMETER,
	ElementType.METHOD})
@Constraint(validatedBy = OrderIdentificatorConstraint.class)
public @interface OrderIdentificator {

	 String message() default "{project.validator.annotation.OrderIdentificator.message}";
	 Class<?>[] groups() default {};
	 Class<? extends Payload>[] payload() default{};
	
}
