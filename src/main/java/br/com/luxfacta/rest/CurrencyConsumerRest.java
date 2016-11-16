package br.com.luxfacta.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.luxfacta.facade.CurrencyFacade;
import br.com.luxfacta.validator.CurrencyInputValidator;

@Path("/eurovariation/")
public class CurrencyConsumerRest {
	private final Logger LOG = Logger.getLogger(CurrencyConsumerRest.class);
	@Autowired
	CurrencyInputValidator validator;

	@Autowired
	CurrencyFacade currencyFacade;

	@GET
	@Path("/init/{init-date}/end/{end-date}")
	public Response getTrack(@PathParam("init-date") String initDate, @PathParam("end-date") String endDate) {
		LOG.info("Begin - Calculate variation - Init:"+initDate +" - End:"+endDate);
		try {
			Map<String,String> r = currencyFacade.getVariationRespost(initDate, endDate, validator);
			return Response.ok(r, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			LOG.info("Error when calculate quotation variation",e);
			Map<String,String> r = new HashMap<String,String>();
			r.put("erro", e.getMessage());
			r.put("type", e.getClass().getSimpleName());
			return Response.status(406).entity(r).type(MediaType.APPLICATION_JSON).build();
		}finally{
			LOG.info("End");
		}
	}

}
