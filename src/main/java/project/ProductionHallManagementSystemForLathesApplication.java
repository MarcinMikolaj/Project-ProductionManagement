package project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class ProductionHallManagementSystemForLathesApplication {

	public static void main(String[] args) {
			
		SpringApplication.run(ProductionHallManagementSystemForLathesApplication.class, args);
	   		
	}
	
	@Bean
	public LocalValidatorFactoryBean createLocalValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

}
