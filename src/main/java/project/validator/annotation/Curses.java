package project.validator.annotation;

import java.lang.annotation.Documented;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

import project.validator.algorithms.CursesConstraint;

import project.validator.algorithms.Dictionary;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({
	ElementType.FIELD,
	ElementType.METHOD,
	ElementType.PARAMETER,
	ElementType.ANNOTATION_TYPE
})
@Constraint(validatedBy = CursesConstraint.class)
public @interface Curses {
	
	public String message() default "message";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	boolean isActive() default true;
	Dictionary[] dictionary() default {Dictionary.ENG};
	
}
