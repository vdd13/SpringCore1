package pl.dom.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientExample {

	
	@Bean 
	public WebClient webClient() {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://localhost:8080")
				.build();
		return webClient;
	}
}
