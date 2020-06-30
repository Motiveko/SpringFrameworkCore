package com.motiveko.testmaven.cli.validator;

import org.springframework.validation.BindException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	
	public static void main(String[] args) {
		
		Person person = new Person("", 200);
		PersonValidator validator = new PersonValidator();
		
		if( validator.supports(person.getClass())) {

			BindException error = new BindException(person, "person");
			
			validator.validate(person, error);
			log.error("+++"+error.getAllErrors());
			
		} else {
			log.info("Invalid Class Type");
		}
		
		
	}
}
