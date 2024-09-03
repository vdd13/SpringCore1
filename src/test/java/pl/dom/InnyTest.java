package pl.dom;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import pl.dom.aop.MojAspect;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("/test.xml")
@SpringJUnitConfig(locations = "/test.xml")
public class InnyTest {

	MojAspect mojA;
	
	@Autowired
	@Qualifier("moj")
	void setMoja(MojAspect moja) {
		this.mojA = moja;
	}
	
//	@Test
	@RepeatedTest(3)
	void letTest() {
		mojA.testPointCutThrowing();
		assertNotNull("asd");
	}
	
}
