package pl.dom;

import java.beans.ConstructorProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
//@DependsOn("Pracownik")
@EnableAspectJAutoProxy
public class Osoba {

//	@Autowired(required = false)
	private Pracownik p; 
	
	public Osoba(Pracownik p) {
		this.p = p;
		
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return p.toString() + p.toString() + "Osoba";
	}
	
	public void setP(Pracownik pracownik) {
		this.p = pracownik;
	}
	
	public Pracownik getP() {
		return p;
	}
}
