package pl.dom.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice( annotations = RestController.class)
public class ErrorAdvice {

	
	@ExceptionHandler(NullPointerException.class)	//dziala jesli nie ma lokalnego Exceptionandling
	public void testControllerAdvice() {
		System.out.println("ControlledAdvice running here !!");
	}
}
