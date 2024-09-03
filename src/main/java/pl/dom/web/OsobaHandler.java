package pl.dom.web;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import pl.dom.model.OsobaService;

public class OsobaHandler {

	private final OsobaService osobaService;
	
	public OsobaHandler(OsobaService osobaService) {
		this.osobaService = osobaService;
		
	}
	
	public ServerResponse findOsoba(ServerRequest request ) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(osobaService.queryList());
	}
}
