package pl.dom.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.dom.model.Osoba;

@Controller
public class FormController {

//	@RequestMapping("/testforms")
//	public ModelAndView  testFormStart() {
//		System.out.println("form START !!");
//		ModelAndView mvc = new ModelAndView();
//		mvc.setViewName("/form.jsp");
//		Osoba osoba = new Osoba();
//		mvc.addObject("osoba", osoba);
//		return mvc;
//	}
//	
//	@RequestMapping("/testform")
//	public void testForm( ) {
//		
//		System.out.println("z formularza " );
//	}
	
}
