package pl.dom;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNullFields;
import jakarta.annotation.Resource;

@Component("Pracownik")
@Scope("singleton")
@Profile("default")
public class Pracownik {

	private final Log logger = LogFactory.getLog(getClass());
	
	int aid = 1;
	String aname = "asd";
	long czas = new Date().getTime();
	
	@Autowired
	@Qualifier("qualifer")
//	@Resource(name="qualifer")
	private Qualifer q;
	
public Pracownik() {
	// TODO Auto-generated constructor stub
	logger.info("PRACOWNIK ZOLSTAL UTWORZONY");
}

//@Autowired
//public Pracownik(@Qualifier("asd") Qualifer q, @Qualifier("qual1") Qualifer q2) {
//	this.q = q2; 
//}

public Pracownik(int aid, String aname) {
	this.aid = aid;
	this.aname = aname;
}



@Override
public String toString() {
	return "Pracownik [aid=" + aid + ", aname=" + aname + ", time= + " + czas + "] "  + q;
}


public String getAThrow() {
	System.out.println("PRACOWNIK THROW !!");
	throw new Error();
}


public int getAid() {
	return aid;
}

public void setAid(int aid) {
	this.aid = aid;
}

public String getAname() {
	return aname;
}

public void setAname(String aname) {
	this.aname = aname;
}	

}
