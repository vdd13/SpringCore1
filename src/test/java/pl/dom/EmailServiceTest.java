package pl.dom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import pl.dom.listeners.BlockedListEvent;
import pl.dom.listeners.EmailService;

@RecordApplicationEvents
//@SpringJUnitConfig
//@TestPropertySource(properties = {"usa:alaska"})
//@TestPropertySource("/application.properties")
@TestPropertySource(locations = "/application.properties", properties = "{usa:newYork}")
//@ContextConfiguration("/appTest.xml") // brakuje @Test na klasach dziedziczenia i leci bład
//@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class EmailServiceTest {

//	@Value("${test.path}")
	@Value("${usa}")
	String path;
	
	@Autowired
	EmailService emailService;
//	 EmailService emailService = new EmailService();
	
	@Autowired
	 ApplicationEvents appEvents;
	
	@Autowired
	ApplicationContext ctx;
	
	  @Configuration 
	  static class Config {
		  @Bean
		  EmailService emailService() {
			  EmailService emailService = new EmailService();
			  return emailService;
		  }
	  }

	
	
	@Test
	public void sendEmail() {
		long eventsCount = 0;
		emailService.sendEmail("test@test.pl");
		eventsCount = appEvents.stream(BlockedListEvent.class).count();
		System.out.println("liczba eventów = " + eventsCount + ", test property = " +path);
		
	}
}
