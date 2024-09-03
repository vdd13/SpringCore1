package pl.dom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import io.micrometer.common.util.StringUtils;

public class ExpressionTests {

	@Autowired
	private ApplicationContext appCtx;
	
	private Integer random  =1;
	
	public ExpressionTests(){

	}
	
	public void callAll() throws NoSuchMethodException, SecurityException {
		ExpressionParser ep = new SpelExpressionParser();
		
		System.out.println(call());
		System.out.println(callConcat());
		System.out.println(callLenght());
		System.out.println(callNew());
		System.out.println(callBeanProperty());
		callEvaluationContext();
		callEvaluationContext2();
		
		System.out.println(random + " " );
		callLiterals();
		callNestedClassProp();
		
		System.out.println(ep.parseExpression("{1,2,3,4,5}").getValue());
		System.out.println(ep.parseExpression("{{'a','b'},{'x','y'}}").getValue());
		
		Map mapa = (Map) ep.parseExpression("{name:'testTEST'}").getValue();
		System.out.println(mapa);
		System.out.println(((int[])ep.parseExpression("new int[]{222,333,444}").getValue())[2]);
		System.out.println(ep.parseExpression("'brwinow'.substring(2,6)").getValue());
		System.out.println(ep.parseExpression("'test ' + 'string' ").getValue());
		System.out.println(ep.parseExpression("1 < -1.11").getValue(Boolean.class));
		System.out.println(ep.parseExpression("1 gt -1.11").getValue(Boolean.class));
		System.out.println(ep.parseExpression("1 == -1.11").getValue(Boolean.class));
		System.out.println(ep.parseExpression("null < -1.11").getValue(Boolean.class));
		System.out.println(ep.parseExpression("'asd' instanceof T(String) ").getValue(Boolean.class));
		System.out.println(ep.parseExpression("'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class));
		System.out.println(ep.parseExpression("7 % 3").getValue(Integer.class));
		System.out.println(ep.parseExpression("true and false").getValue(Boolean.class));
		System.out.println(ep.parseExpression("true or false ").getValue(Boolean.class));
		setValueByParser();
		System.out.println(ep.parseExpression("T(java.lang.Math).random() * 100.0").getValue());
		setNewObjectByParser();
		callthisroot();
		callFunction();
		callBean();
		callIfElse();
		callElvisOper();
		callEvaluation();
	}
	
	public void callLiterals() {
		ExpressionParser ep = new SpelExpressionParser();
		System.out.println(ep.parseExpression("-6.02").getValue(Double.class));
		System.out.println((Boolean) ep.parseExpression("!true").getValue());
	}
	
	public String call() {
	
	ExpressionParser eParser  = new SpelExpressionParser();
	Expression ex= eParser.parseExpression("'Hello !'");
	String str = (String) ex.getValue();	
	return str;
	}
	
	public String callConcat() {
		
	ExpressionParser eParser  = new SpelExpressionParser();
	Expression ex= eParser.parseExpression("'Hello !'.concat('!!!')");
	String str = (String) ex.getValue();	
	return str;
	}
	
	public int callLenght() {
		ExpressionParser eParser = new SpelExpressionParser();
		Expression ex = eParser.parseExpression("'Hello'.bytes.length");
		
		return (Integer) ex.getValue();
	}

	public String callNew() {
		ExpressionParser ePArser = new SpelExpressionParser();
		Expression ex = ePArser.parseExpression("new String('helloWorld').toUpperCase()");
		return ex.getValue(String.class);
	}
	
	public boolean callBeanProperty() {
		Pracownik p = new Pracownik();
		p.setAname("TOny");
		
		ExpressionParser eParser = new SpelExpressionParser();
		Expression ex = eParser.parseExpression("aname");
		String name = (String) ex.getValue(p);
		
		System.out.println(name);
		ex= eParser.parseExpression("aname == 'TOny'");
		boolean result = ex.getValue(p, Boolean.class);
		
		return result;
	}
	
	public void callEvaluationContext(){
		Simple s = new Simple();
		s.booleanList.add(true);

		EvaluationContext ec = SimpleEvaluationContext.forReadOnlyDataBinding().build();
		
		ExpressionParser eParser = new SpelExpressionParser();
		eParser.parseExpression("booleanList[0]").setValue(ec, s, "false"); //dodaje nowa wartosc
		Boolean b = s.booleanList.get(0);
		
		System.out.println(b);
	}
	
	public void callEvaluationContext2() {
		Simple simp = new Simple();
		ExpressionParser ep = new SpelExpressionParser();
		EvaluationContext ectx = SimpleEvaluationContext.forReadWriteDataBinding().build();
		ectx.setVariable("newname", "TOJESTTEN");
		
		ep.parseExpression("urodziny.miasto = #newname").getValue(ectx, simp);
		System.out.println("eval2 = " + simp.urodziny.getMiasto());
	}
	
	
	public void callNestedClassProp() {
		Simple s = new Simple();
		ExpressionParser ep = new SpelExpressionParser();
		
		System.out.println(ep.parseExpression("urodziny.miasto").getValue(s));
		System.out.println(ep.parseExpression("urodziny.Rok + 1900").getValue(s));
		System.out.println(ep.parseExpression("urodziny.Lista[2]").getValue(s));
		System.out.println(ep.parseExpression("urodziny.mapa[2]").getValue(s));

	}
	
	public void setValueByParser() {
		Simple s = new Simple();
		ExpressionParser ep = new SpelExpressionParser();
		ep.parseExpression("name").setValue(s, "Alek");
		System.out.println(ep.parseExpression("name").getValue(s));
	}
	
	public void setNewObjectByParser() {
		Simple s = new Simple();
		ExpressionParser ep = new SpelExpressionParser();
		ExpressionTests et = ep.parseExpression("new pl.dom.ExpressionTests()").getValue(ExpressionTests.class);
		System.out.println(ep.parseExpression("random").getValue(et));
	}
	
	public void callthisroot() {
		List<Integer> primes = new ArrayList<Integer>();
		primes.addAll(Arrays.asList(2,3,5,7,11,13,17));
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
		context.setVariable("primes", primes);
		
		System.out.println( (List<Integer>) parser.parseExpression("#primes.?[#this>10]").getValue(context));
	}
	
	public void callFunction() throws NoSuchMethodException, SecurityException {
//		ExpressionParser parser = new SpelExpressionParser();
//		EvaluationContext ctx = SimpleEvaluationContext.forReadWriteDataBinding().build();
//		ctx.setVariable("reverseString", StringUtils.class.getDeclaredMethod("reverseString", String.class));
//		System.out.println(parser.parseExpression("#reverseString('helloooo')").getValue(ctx, String.class));
	}
	
	public void callBean() {
		BeanFactory factory = (BeanFactory) appCtx;
		ExpressionParser ep = new SpelExpressionParser();
		StandardEvaluationContext ctx = new StandardEvaluationContext();
		ctx.setBeanResolver(new BeanFactoryResolver(factory));
		ValueAddnotationTest valuTest = (ValueAddnotationTest) ep.parseExpression("@valueAd").getValue(ctx);
		System.out.println(valuTest);
	}
	
	public void callIfElse() {
		ExpressionParser parser = new SpelExpressionParser();
		System.out.println(parser.parseExpression(" false ? 'toTrue' : 'toFalse'").getValue());
	}
	
	public void callElvisOper() {
		ExpressionParser parser = new SpelExpressionParser();
		System.out.println(parser.parseExpression("aname?: 'BrakDanych'" ).getValue(new Pracownik(), String.class)); // jesli aname jest null to 'BrakDAnych'
	}
	
	public void callEvaluation() {
		ExpressionParser parser = new SpelExpressionParser();
		System.out.println(parser.parseExpression(" random number is #{T(java.lang.Math).random()}", new TemplateParserContext()).getValue(String.class));
	}
	
	
	class Simple {
		  public List<Boolean> booleanList = new ArrayList<Boolean>();
		  public String name = "allTony";
		  public Urodziny urodziny = new Urodziny();
	
		  class Urodziny {
			
			public String miasto = "warszawa";
			public Integer rok = 1929;
			List<String> lista = List.of("to1", "to2", "to3");
			Map<Integer, String> mapa = Map.of(1, "Jeden", 2, "DWA"); 
			
			  
			  public String getMiasto() {
					return miasto;
				}
				public void setMiasto(String miasto) {
					this.miasto = miasto;
				}
				public Integer getRok() {
					return rok;
				}
				public void setRok(Integer rok) {
					this.rok = rok;
				}
				public List<String> getLista() {
					return lista;
				}
				public void setLista(List<String> lista) {
					this.lista = lista;
				}
				public Map<Integer, String> getMapa() {
					return mapa;
				}
				public void setMapa(Map<Integer, String> mapa) {
					this.mapa = mapa;
				}
				
			
		  }
			public void setName(String name) {
				this.name = name;
			}
			public String getName() {
				return name;
			}
	}
	
	public void setRandom(Integer random) {
		this.random = random;
	}

	public Integer getRandom() {
		return random;
	}
	
}
