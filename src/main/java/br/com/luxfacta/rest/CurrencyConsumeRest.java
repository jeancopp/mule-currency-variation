package br.com.luxfacta.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@Path("/")
public class CurrencyConsumeRest {

	CurrencyInputValidator validator = new CurrencyInputValidator();
	
	@GET
	@Path("/")
	public Response info() {
		return Response.ok("{teste}", MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/init/{init-date}/end/{end-date}")
	public Response getTrack(@PathParam("init-date")String initDate, @PathParam("end-date")String endDate) {
		validator.validateDate(initDate);
		validator.validateDate(endDate);
		
		LocalDate date = validator.converDate(initDate);
		LocalDate date2 = validator.converDate(endDate);
		System.out.println(date);
		System.out.println(date2);
		
//		System.out.println(validator);
		LocalDate dataInicial = LocalDate.of(2016, 11, 15);
		LocalDate dataFinal = LocalDate.of(2016, 11, 16);
		dataInicial.format(DateTimeFormatter.ISO_DATE);
		dataFinal.format(DateTimeFormatter.ISO_DATE);
		System.out.println(dataInicial);System.out.println(dataFinal);
		
		CurrencyFacade currencyFacade = new CurrencyFacade();
		double variation = currencyFacade.variationOf(dataInicial,dataFinal);
		
		return Response.ok("Variation:"+variation, MediaType.TEXT_HTML).build();	
	}
}
