package pl.dom;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Errors;

import pl.dom.listeners.EmailService;
import pl.dom.model.Osoba;
import pl.dom.model.OsobaRepo;
import pl.dom.model.OsobaService;
import pl.dom.model.OsobaServiceTx;

import pl.dom.validator.DzieckoValidator;
import pl.dom.web.WebClientExampleUSE;

@SpringBootApplication
@ImportResource("appTest.xml")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringCore1Application {
	
	public static void main(String[] args) throws IOException, NoSuchMethodException, SecurityException {

		AnnotationConfigServletWebServerApplicationContext ctx = (AnnotationConfigServletWebServerApplicationContext) SpringApplication.run(SpringCore1Application.class, args);

		WebClientExampleUSE webC = ctx.getBean("WebClientExampleUSE", WebClientExampleUSE.class);
		webC.testGet();
		
		
		
//********************************DB****************************************************************

//		OsobaService os= ctx.getBean("osobaService", OsobaService.class);
//		os.runQueries();
		
		
//******************************** tranzakcje**********************************************************
//		OsobaServiceTx tx = ctx.getBean("osobaServiceTx", OsobaServiceTx.class);
//		tx.serviceProgramaticallyTransactionmanager();
		
//		Osoba oso =  (Osoba)tx.serviceMethod();
//			System.out.println(oso == null ? "null" : "nie null");
		
		
//		OsobaService osoba = ctx.getBean("osobaService", OsobaService.class);
//		Optional<pl.dom.model.Osoba> o = osoba.getOsobarepo().findById(1);
//		System.out.println(o.get().getNazwisko());
//		
//		pl.dom.model.Osoba nowy = new pl.dom.model.Osoba();
//		nowy.setId(2322);
//		nowy.setImie("adam");
//		nowy.setNazwisko("yo");
//		nowy.setNumer(22233);
//	 
//		osoba.createOsob(nowy);;
//	
		
//		 osoba = ctx.getBean("fooService", OsobaService.class);
//		osoba.createOsob(nowy);
		
//********************************* core*****************************************************************
//	//	ApplicationContext ctx = new ClassPathXmlApplicationContext("appTest.xml");
//		System.out.println("3");
//		
//		EmailService es = ctx.getBean("emailService", EmailService.class);
//		es.sendEmail("test@test.pl"); //test@test.pl
//		
// //		ctx.getEnvironment().setActiveProfiles("default");  ///	dla	AnnotationConfigServletWebServerApplicationContext ctx 
//		System.out.println("default profiles : " + ctx.getEnvironment().getDefaultProfiles());
//		System.out.println("is there my property in ctx? " + ctx.getEnvironment().containsProperty("imie") + " imie: " +ctx.getEnvironment().getProperty("imie"));
//		
//		MessageSource msgSource = ((MessageSource) ctx);
//		System.out.println(msgSource.getMessage("message", new Object[] {"placeholderExample"}, "jesli nie ma znalezionej message to ten text", Locale.ITALY));
//		
//		
//		Pracownik b1 = ctx.getBean("Pracownik", Pracownik.class);
//		try {
//			b1.getAThrow();
//		}catch (Error er) {
//			
//		}
//		System.out.println("FAILED !! " + b1.getAname());
//		
//		Osoba os = ctx.getBean("osoba", Osoba.class);
//		Mana mana = ctx.getBean("Mana", Mana.class);
//		Dziecko dziec = ctx.getBean("Dziecko", Dziecko.class);
//		ValueAddnotationTest valueAd = ctx.getBean("valueAd", ValueAddnotationTest.class);
//		BeanAddnotationTest beanAd = ctx.getBean("beanTest", BeanAddnotationTest.class);
//		List<String> list = (List) ctx.getBean("namedList");
//		ContructorInjectionTest cit = ctx.getBean("contructorInjectionTest", ContructorInjectionTest.class);
//		DzieckoValidator dv = ctx.getBean("dzieckoValidator", DzieckoValidator.class);
//		Dziecko dziecko = new Dziecko(new Pracownik());
//		dziecko.setName(null);
//		Errors e = dv.validateObject(dziecko);
//		System.out.println(e);
//		
//		
//		ResourceValueBean resourceValue = ctx.getBean("resValBean", ResourceValueBean.class);
//		System.out.println("resource value bean = " + resourceValue.getResource().getFile());
//		
//		
//		System.out.println(b1);
//		System.out.println(os);
//		System.out.println(dziec);
// //		((AbstractApplicationContext)ctx).close();
//		
//		System.out.println("test value with PropertySource from application.properties " + valueAd);
//		System.out.println("test beanAdnotation " + beanAd);
//		
//		System.out.println("test @bean list from method" + list);
//		
//		System.out.println("Constructor constructor  injection pracowink " + cit.toString());
//		
//
//		ExpressionTests et = ctx.getBean("ELTests", ExpressionTests.class);
//		System.out.println("spEL random value from xml = " +et.getRandom());
//		et.callAll();
//		
//		beforeAdviceAOPTest(b1);
		
	}
	
	
	public static void beforeAdviceAOPTest(Pracownik b1) {
		ProxyFactory pf = new ProxyFactory(new Pracownik()); // jesli klasa implementuje interfejs to proxy wymaga działan na interfejsie
//		ProxyFactory pf = new ProxyFactory();
//		pf.setTarget(b1);
		pl.dom.aop.BeforeAdvice beforeAdvidce= new pl.dom.aop.BeforeAdvice();
		pf.addAdvice(beforeAdvidce);
		Pracownik pracownik = (Pracownik) pf.getProxy();
		Advised adv = (Advised) pracownik;
		System.out.println("Advices : " + adv.getAdvisors().length + ", advice " + (adv.getAdvisors())[0].toString() +  ", target " + adv.getTargetSource());
		
		System.out.println(pracownik.getAname());
		System.out.println(pracownik.getAid());
	}
	
}
