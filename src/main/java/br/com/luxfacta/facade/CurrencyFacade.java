package br.com.luxfacta.facade;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyFacade {

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
		
		String uri = "http://apilayer.net/api/historical?access_key=76ebd4dc9b17550237afc631dd3b3e8c&date="+date+"&currencies=EUR&format=1";
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
}
