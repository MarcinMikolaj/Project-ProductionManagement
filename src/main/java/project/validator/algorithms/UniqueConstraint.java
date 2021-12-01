package project.validator.algorithms;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import project.validator.annotation.Unique;

import project.repositories.SuperRepositoryImpl;
import project.repositories.SuperRepository;

public class UniqueConstraint implements ConstraintValidator<Unique, String> {
	
	public void initialize(Unique annotation) {
		
		SuperRepository<?> x = annotation.implementation().cast(getClass());
		
	
		
		Class<?> obj =  annotation.implementation();
		

		
		//obj.createUpdateOrDeleteQuery(myQuery);
	}
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return false;
	}

}
