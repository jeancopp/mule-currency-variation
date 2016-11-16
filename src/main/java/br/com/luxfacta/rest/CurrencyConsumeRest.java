package br.com.luxfacta.rest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import br.com.luxfacta.facade.CurrencyFacade;
import br.com.luxfacta.validator.CurrencyInputValidator;


@Path("/")
public class CurrencyConsumeRest {
	private final Logger LOG = Logger.getLogger(CurrencyConsumeRest.class);
	private CurrencyInputValidator validator = new CurrencyInputValidator();

	@GET
	@Path("/")
	public Response info() {
		return Response.ok("{teste}", MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/init/{init-date}/end/{end-date}")
	public Response getTrack(@PathParam("init-date") String initDate, @PathParam("end-date") String endDate) {
		LOG.info("Begin - Calculate variation - Init:"+initDate +" - End:"+endDate);
		try {
			LocalDate dataInicial = validator.convertDate(initDate);
			LocalDate dataFinal = validator.convertDate(endDate);

			CurrencyFacade currencyFacade = new CurrencyFacade();
			double variation = currencyFacade.variationOf(dataInicial, dataFinal);

			Map<String,String> r = new HashMap<String,String>();
			r.put("variation", String.valueOf(variation));
			r.put("inital-date", initDate);
			r.put("end-date", endDate);
			return Response.ok(r, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			LOG.info("Error when calculate quotation variation",e);
			Map<String,String> r = new HashMap<String,String>();
			r.put("erro", e.getMessage());
			r.put("type", e.getClass().toGenericString());
			LOG.info(r);
			return Response.status(406).entity(r).type(MediaType.APPLICATION_JSON).build();
		}finally{
			LOG.info("End");
		}
	}
}
