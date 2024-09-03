package pl.dom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import pl.dom.listeners.EmailService;

@Configuration
public class BaseConfig {

	 public EmailService emailService;
	
	 @Bean
	EmailService getEmailService() {
		return emailService;
	}

	void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	
	
	
}
