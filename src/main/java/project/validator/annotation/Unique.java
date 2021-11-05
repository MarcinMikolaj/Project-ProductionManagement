package project.validator.annotation;

import java.lang.annotation.Documented;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

import project.validator.algorithms.UniqueConstraint;

import project.repositories.SuperRepositoryImpl;
import project.repositories.AccountRepositoryImpl;
import project.repositories.SuperRepository;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,
	ElementType.FIELD,
	ElementType.PARAMETER,
	ElementType.METHOD})
@Constraint(validatedBy = UniqueConstraint.class)
public @interface Unique {
	
	String message() default "The value ${validatedValue} is already assigned to another object";
	Class<?>[] grups() default {};
	Class<? extends Payload>[] payload() default {};
	Class<? extends SuperRepository<?>> implementation() default AccountRepositoryImpl.class;

}
