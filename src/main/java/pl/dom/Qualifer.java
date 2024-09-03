package pl.dom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Qualifer {
 
	private String str = "testQuali";

	
	@Autowired(required = false)
	@Qualifier("namedList")
	List list;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	
	@Bean(initMethod = "initm", destroyMethod = "destroym", name = "beanTest")
	public BeanAddnotationTest beanTest() {
		
		return new BeanAddnotationTest();
	}
	
	
	@Override
	public String toString() {
	
		return str + " " + list;
	}
}
