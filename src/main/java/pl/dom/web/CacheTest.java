package pl.dom.web;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@Configurable
@EnableCaching
public class CacheTest {

	@Cacheable("test")
	public String testData() {
		java.util.Date date2 = new java.util.Date();
		return date2.toString();
	}
}
