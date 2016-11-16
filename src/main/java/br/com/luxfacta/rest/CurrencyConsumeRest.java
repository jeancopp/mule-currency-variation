package br.com.luxfacta.rest;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-variation/")
public class CurrencyConsumeRest {

	@RequestMapping(produces = MediaType.APPLICATION_JSON)
	public String info() {
		return "{}";
	}
	
	@RequestMapping(value = "/init/{init-date}/end/{end-date}", 
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getTrack(@PathParam("init-date")String artistaNome, @PathParam("end-date")String track) {
		return new ResponseEntity<>(HttpStatus.OK);	
	}
}
