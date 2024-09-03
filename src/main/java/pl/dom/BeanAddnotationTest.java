package pl.dom;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class BeanAddnotationTest {

	@Value("Pruszków")
	private String miasto;
	
	
	private String ulica;
	private String numer;
	
	public String getMiasto() {
		return miasto;
	}
	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}
	public String getUlica() {
		return ulica;
	}
	
	@Value("Prusa")
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getNumer() {
		return numer;
	}
	
	@Value("213")
	public void setNumer( String numer) {
		this.numer = numer;
	}
	
	@Override
	public String toString() {
	
		return "BeanAddnotationTest" + miasto + " " + ulica + " " + numer ;
	}
	
	@Bean
	public List<String> namedList(){
		return Arrays.asList(new String[] {"jeden", "dwa", "trzy"});
	} 
	
	public void initm() {
		System.out.println("BeanAdon..Test initm");
		
	}
	
	public void destroym() {
		System.out.println("BeanAdon..Test destroy");
		
	}
	
}
