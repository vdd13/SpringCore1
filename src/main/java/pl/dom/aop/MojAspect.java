package pl.dom.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Aspect
//@Component  //wylaczone dla testów xml
//@Order(value = 1)
public class MojAspect {

	@DeclareParents(value = "pl.dom.*", defaultImpl = DefaultUsageTrackerImpl.class)
	public static UsageTrackerI mixin;
	
	
//	@Pointcut("within(pl.dom..*)")
//	@Pointcut("execution(* getAname(..))")
//	@Pointcut("within(pl.dom.Pracownik)")
//	@Pointcut("execution(* getAname())")
//	@Pointcut("execution(String getAname(*))") //jeden lub wiecej parametrów
//	@Pointcut("execution(String getAname(..))") //dowolny parameter
//	@Pointcut("execution(public String getAname())")
//	@Pointcut("execution(public String getA*())")
//	@Pointcut("execution(* pl.dom.Pracownik.*(..))") //kazda funkcja z klasy pracownik
//	@Pointcut("within(pl.dom.*)") //kazda funkcja z pakietu
//	@Pointcut("bean(Pracownik)") // keidy metody z beana Pracownik
	@Pointcut("execution(public String getAname())")
	public void testPointCut() {
		System.out.println("testPointCUT!!!!!!!!!!");
	}
	
	
	@Pointcut("execution(public String getAThrow())")
	public void testPointCutThrowing() {
System.out.println("THROWWWWWWWWWWW " );
	}
	
	
	@Before("testPointCut()")
//	@Before("bean(Pracownik)")
//	@Before("execution(public String getAname())")
	public void yoloBefore() {
		System.out.println("testPointCUT!!!!!!!!!! YOLO-BEFORE");
	}

	@AfterReturning("testPointCut()")
	public void yolo() {
		System.out.println("testPointCUT!!!!!!!!!! YOLO-AFTERRETURNIG AP=1");
	}


	@AfterReturning(pointcut = "testPointCut()", returning = "result") //result obiekt z metody 'String getAname()' joinPoint - @Pointcut("execution(public String getAname())")
	public void returingValue(String result) {
		System.out.println("testPointCUT!!!!!!!!!! YOLO-AFTERRETURNIG  AP=1 + Object " + result);
	}
	
	@AfterThrowing(pointcut = "testPointCutThrowing()", throwing = "error")
	public void afterThrowing(Error error) {
		System.out.println("testPointCUT!!!!!!!!!! + THROWWW " + error);
	}
	
	@After("execution(public String getAname())")
	public void afterAkaFinally() {
		System.out.println("testPointCUT!!!!!!!!!! FINALLY POINTCUT");
	}
	
	@Around("execution(public String getAname())")
	public Object aroundTest(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("testPointCUT!!!!!!!!!! AROUND  POINTCUT JoinPoint");
		Object obj = joinPoint.proceed();
		
		Object objthis = joinPoint.getThis(); // Pracownik.toString()
		Object objtarget = joinPoint.getTarget(); 
		Object objsignature = joinPoint.getSignature(); //pl.dom.Pracownik.getAname()
		Object objargs = joinPoint.getArgs();
		System.out.println("testPointCUT!!!!!!!!!! AROUND  POINTCUT JoinPoint" + obj.toString() +", sig "+ objsignature + ", args "+ objargs + ", this " + objthis + ", target " +objtarget);
		return obj;
	}
	
	@Before(value="testPointCut()")
	public void beforeParamPass(JoinPoint jp) {
		System.out.println("testPOINTCUT jp " + jp);
	
	}
	
	@Before("testPointCut() && this(usageTracker)") //korzystanie z klasy z dodatkowa logika biznesowa
	public void beforeIntroduction(UsageTrackerI usageTracker) {
		usageTracker.sleep();
	}
}
