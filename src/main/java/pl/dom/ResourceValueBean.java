package pl.dom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component("resValBean")
public class ResourceValueBean {

	private Resource resource;
	
	public ResourceValueBean(@Value("${test.path}") Resource resource) {
		this.resource = resource;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	
}
