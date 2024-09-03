package pl.dom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("valueAd")
@PropertySource("classpath:application.properties")
public class ValueAddnotationTest {

//	@Value("${imie1: albo to}")
	private String testValue;
	
//	public ValueAddnotationTest(@Value("${imie1: albo to}") String testValue) {
//		this.testValue = testValue;
//	}
	
	@Value("${imie: albo to}")
	public void setTestValue( String testValue) {
		this.testValue = testValue;
	}
	
	@Override
	public String toString() {
	
		return testValue;
	}
}
