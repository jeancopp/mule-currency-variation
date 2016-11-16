package br.com.luxfacta.validator;

import java.time.LocalDate;

import org.joni.exception.ValueException;
import org.springframework.stereotype.Component;

@Component
public class CurrencyInputValidator {
	
	public void validateDate(String date){
		if(!date.matches("\\d{4}-\\d{2}-\\d{2}"))
			throw new ValueException("Invalid date format");
		convertDate(date);
	}
	
	public LocalDate convertDate(String possibleDate){
		return LocalDate.parse(possibleDate);
	}
	
}
