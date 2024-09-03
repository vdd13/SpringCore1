package pl.dom.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
//@Order(value = 2)
public class MojAspectOrderTEst {

	@Pointcut("execution(public String getAname())")
	public void testAspectOrder() {
		
	}
	
	@AfterReturning("testAspectOrder()")
	public void callsyso() {
		System.out.println("TO JEST ASPECT 2 ");
	}
}
