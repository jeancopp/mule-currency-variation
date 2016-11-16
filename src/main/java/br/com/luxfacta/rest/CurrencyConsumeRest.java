package br.com.luxfacta.rest;

import java.time.LocalDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	public Response getTrack(@PathParam("init-date")String initDate, @PathParam("end-date") String endDate) {
		try {
			validator.validateDate(initDate);
			validator.validateDate(endDate);

			LocalDate dataInicial = validator.converDate(initDate);
			LocalDate dataFinal = validator.converDate(endDate);

			CurrencyFacade currencyFacade = new CurrencyFacade();
			double variation = currencyFacade.variationOf(dataInicial, dataFinal);

			return Response.ok("Variation:" + variation, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(406).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
}
