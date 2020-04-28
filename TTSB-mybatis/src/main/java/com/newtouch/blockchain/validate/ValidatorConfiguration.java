package com.newtouch.blockchain.validate;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 校验器
 * @Package com.newtouch.blockchain.validate
 * @ClassName: ValidaterConfiguration
 */
@Configuration
public class ValidatorConfiguration {
	
	@Bean
	public Validator getValidator() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
		        .configure()
		        .ignoreXmlConfiguration()
		        .failFast(false)
		        .constraintValidatorFactory(null)
		        .buildValidatorFactory();
		
		return validatorFactory.getValidator();
	}
}
