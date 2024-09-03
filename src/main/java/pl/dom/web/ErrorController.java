package pl.dom.web;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController //json jako return 
//@Controller // // strony jako return "home.jsp"
@RequestMapping(value = "/aaa")
public class ErrorController {
	
	@GetMapping("/data")
	public String testCache() {
		return (new CacheTest()).testData();
	}
	
	@GetMapping("/responseEntity2")
	public ResponseEntity<String> testResponseEntity2(){
		Instant date = Instant.now();
		java.util.Date date2 = new java.util.Date();
		return  ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES)).body(date.toString());
	}
	
	@GetMapping("/responseEntity")
	public ResponseEntity<String> testResponseEntity(){
		return ResponseEntity.ok().eTag("to jestETAG").body("to jest BODY");
	}
	
	@RequestMapping(path="/error")
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public Map<String, Object> handle(HttpServletRequest req){
		System.out.println("cos ");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", req.getAttribute("jakarta.servlet.error.status_code"));
		map.put("reason", req.getAttribute("jakarta.servlet.error.message"));
		map.put("ole", "oleoleole");
		return map;
	}
	
	@RequestMapping(path = "/hello", method=RequestMethod.GET, consumes = "aplication/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String requestMethodName() {
		System.out.println("Prams ");
		return "asd";
	}
	
	@RequestMapping("hi")
	public String handle(Model model) {
		System.out.println("TUT TU " + model);
		model.addAttribute("message", "Hi world");
		return "/home.jsp";
	}
	
	@RequestMapping("hi1")
	public ModelAndView handleMAV(Model model) {
		System.out.println("TUT TU " + model);
		model.addAttribute("message", "Hi world");
		return new ModelAndView("/home.jsp");
	}

	@GetMapping(path="/a/{id}")  
	public void handleGet(@PathVariable String id) {
		System.out.println("Pathvariable id = " +id);
	}
	
	
//	@GetMapping(path="/a/{id}", params="myParam=2")  // testuje czy w url jest ?myParam=2
//	public void handleGet(@PathVariable String id) {
//		System.out.println("Pathvariable id = " +id);
//	}
	
	@GetMapping("/param") // /aaa/param?param=1
	public void getMethodName(@RequestParam String param, HttpServletRequest req, @RequestHeader("Accept-Language") String lang, @RequestHeader("Accept") String type) {
		HttpSession session = req.getSession();
		session.setAttribute("asd", "cooking");
		System.out.println("Param " + param + ", session " + session.getAttribute("asd") + ", Language = " + lang + ", acceptedType = " +type);
	}
	
	@RequestMapping("/testExcepion")
	public void callTestException() {
		Date date = null;
		System.out.println("date " + date.getDate());
	}
	
//	@ExceptionHandler(NullPointerException.class)
//	public String testException() {
//		System.out.println("TEST EXCEPTION!!");
//		return "yo";
//	}
	
	
}
