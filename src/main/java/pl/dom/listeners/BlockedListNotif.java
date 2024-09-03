package pl.dom.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BlockedListNotif implements ApplicationListener<BlockedListEvent>{

	private String notifAdress;
	
	public void setNotifAdress(String notifAdress) {
		this.notifAdress = notifAdress;
	}
	
	@Override
	public void onApplicationEvent(BlockedListEvent event) {
		System.out.println("!!!!!!!!!!!!!!!!! to jest notif !!!!!!!!!!!!!!!!!!!!!!!!!! " +event.getAdress());
		
	}

}
