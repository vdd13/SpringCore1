package pl.dom.listeners;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;


public class  BlockedListEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private String adress;
	
	public BlockedListEvent(Object source, String adress) {
		super(source);
		this.adress = adress;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	

}
