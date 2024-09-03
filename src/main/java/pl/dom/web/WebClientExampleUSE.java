package pl.dom.web;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service(value = "WebClientExampleUSE")
public class WebClientExampleUSE {

	private final WebClient webClient;
	
	
	public WebClientExampleUSE(WebClient webClient) {
		this.webClient = webClient;
	}
	
	
	public void testGet() {
		System.out.println("hi " + webClient.get()
		.uri("/aaa/responseEntity2") // responseEntity, responseEntity2
		.retrieve()
		.bodyToMono(String.class)
		.block());
		
	}
}
