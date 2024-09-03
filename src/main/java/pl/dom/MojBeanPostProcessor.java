package pl.dom;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.lang.Nullable;

public class MojBeanPostProcessor implements BeanPostProcessor {

	
	
	@Nullable
	public  Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Bean1 '" + beanName + "' created : " + bean.toString());
		return bean;
	}

	@Nullable
	public  Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Bean2 '" + beanName + "' created : " + bean.toString());
		return  bean;
	}
	
}
