package project.validator.algorithms;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import project.validator.annotation.OrderIdentificator;

import project.repositories.materials.MaterialOrderRepository;

public class OrderIdentificatorConstraint implements ConstraintValidator<OrderIdentificator, String> {
	
	private MaterialOrderRepository materialOrderRepository;
	
	@Autowired
	public OrderIdentificatorConstraint(MaterialOrderRepository materialOrderRepository) {
		this.materialOrderRepository =  materialOrderRepository;
	}
	
	@Override
	public void initialize(OrderIdentificator annotation) {	
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value == null || value.length() <= 0)
			return false;
		
		return materialOrderRepository.existByIdentificator(value);
	}

}
