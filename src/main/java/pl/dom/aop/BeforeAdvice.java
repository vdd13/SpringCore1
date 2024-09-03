package pl.dom.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class BeforeAdvice implements MethodBeforeAdvice {

	private int ilosc = 0;
	
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		ilosc++;
		System.out.println("BEFORE ADVICE AOP PROXY***************** " +method + "  " + getIlosc());
	}

	public int getIlosc() {
		return ilosc;
	}
	
}
