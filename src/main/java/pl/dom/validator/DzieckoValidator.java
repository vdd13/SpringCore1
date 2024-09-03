package pl.dom.validator;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.dom.Dziecko;

@Component
public class DzieckoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Dziecko.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("validation from DzieckoValidator");
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
		
		Dziecko dz = (Dziecko) target;
		System.out.println("Name " + dz.getName());
		if(Strings.isEmpty(dz.getName()))
			errors.rejectValue("name", "null.or.empty");
	
//		System.out.println(errors.getAllErrors());
	}

}
