package project.validator.algorithms;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import project.validator.annotation.TaxIdentificatorNumberIsaDataBase;

import project.repositories.materials.MaterialProviderRepository;

public class TaxIdentificatorNumberIsaDataBaseConstraint implements ConstraintValidator<TaxIdentificatorNumberIsaDataBase, String> {
	
	private MaterialProviderRepository materialProviderRepository;
	
	@Autowired
	public TaxIdentificatorNumberIsaDataBaseConstraint(MaterialProviderRepository materialProviderRepository) {
		this.materialProviderRepository = materialProviderRepository;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value == null || value.length() <=0)
			return false;
		
		return materialProviderRepository.existByNip(value);
		
	}

}
