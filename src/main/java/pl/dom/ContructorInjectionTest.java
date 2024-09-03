package pl.dom;

import org.springframework.stereotype.Component;

@Component
public class ContructorInjectionTest {

	private Pracownik p;
	
	public ContructorInjectionTest(Pracownik p) {
		this.p = p;
	}
	
	@Override
	public String toString() {
	
		return p.toString();
	}
}
