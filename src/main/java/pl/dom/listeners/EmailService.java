package pl.dom.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class EmailService implements ApplicationEventPublisherAware{

	private ApplicationEventPublisher publisher;
	private String adressBlocked = "test@test.pl";
	
	@Override
//	@Autowired
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
		
	}
	
	public void sendEmail(String adress) {
		if(adressBlocked.equalsIgnoreCase(adress)) {
			System.out.println("***** adress do blokowania ***** " + adress);
			publisher.publishEvent(new BlockedListEvent(this, adress));
		}else {
			System.out.println("*****adress email poprawny " + adress);
		}			
		// itd.
	}

}
