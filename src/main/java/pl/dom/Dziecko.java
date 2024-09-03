package pl.dom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Component("Dziecko")
public class Dziecko extends Osoba{

	public Dziecko(Pracownik p) {
		super(p);
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		System.out.println("Dzeicko " + super.toString() + " " +getName());
		return null;
	}
	
}
