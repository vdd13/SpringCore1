package pl.dom;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Mana {

	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@PostConstruct
	public void init() {
		
		System.out.println("Wywolanie Mana " + getName());
	}
	
//	@PreDestroy
	public void remove() {
		
		System.out.println("Remove Mana " + getName());
	}
	
	
	@Override
	public String toString() {
	
		return "to jest MAna";
	}
}
