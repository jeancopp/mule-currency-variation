package br.com.luxfacta.facade;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.luxfacta.validator.CurrencyInputValidator;

@Component
public class CurrencyFacade {
	@Value("${app.accesskey}")
	private String accessKey;
	
	public double variationOf(LocalDate initialDate, LocalDate finalDate) {
		try{
			double initialCotation = getCotationOf(initialDate);
			double finalCotation = getCotationOf(finalDate);
			return finalCotation/initialCotation - 1;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	private final double getCotationOf(LocalDate date){
		
		RestTemplate restTemplate = new RestTemplate();
		
		String uri = "http://apilayer.net/api/historical?access_key="+accessKey+"&date="+date+"&currencies=EUR&format=1";
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = restTemplate.getForEntity(uri, Map.class);
		if(!entity.getStatusCode().equals(HttpStatus.OK)){
			throw new RuntimeException("Error on get information");
		}
		Map<?, ?> body = entity.getBody();
		Map<?,?> object = (Map<?,?>)body.get("quotes");
		Double cotation = (Double) object.get("USDEUR");
		
		return cotation;
	}

	public Map<String, String> getVariationRespost(String initDate, String endDate, CurrencyInputValidator validator) {
		LocalDate init = validator.convertDate(initDate);
		LocalDate end = validator.convertDate(endDate);
		double variation = variationOf(init, end);

		Map<String,String> r = new HashMap<String,String>();
		r.put("variation", String.valueOf(variation));
		r.put("inital-date", initDate);
		r.put("end-date", endDate);
		
		return r;
	}
}
