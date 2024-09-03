package pl.dom.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class MojAspectXml {

	
	public void callBefore() {
		System.out.println("BEFORE XML ");
	}
	
	public void callBeforeAname(Object service) { // Pracownik.getAname() this(servis) zwraca obiekt prcownik
		System.out.println("BEFORE ANAME XML + this: " + service);
	}
	
	public void callAfterAname(Object returning) { // wartosc 'returning' zwracana z metody dla której jest aop wywoływane 'String Pracownik.getAname()'
		System.out.println("AFTER RETURNING XML + returning: " + returning);
	}
	
	public void callAfterThrow() {
		System.out.println("AFTER THROW XML ");
	}
	
	public void callAfterThrowWithError(Error err) {
		System.out.println("AFTER THROW XML + error: " + err);
	}
	
	public void callAfterAname() {
		System.out.println("AFTER (finally) ANAME XML ");
	}
	
	public Object callAroundAname(ProceedingJoinPoint pjp ) throws Throwable {
		Object obj = pjp.proceed();
		System.out.println("AROUND ANAME XML obj: " + obj.toString());
		return obj;
	}
	
	public void beforeIntroduction(UsageTrackerI usageTracker) {
		System.out.println("Introduction -----------------------------");
		usageTracker.sleep();
	}
}
