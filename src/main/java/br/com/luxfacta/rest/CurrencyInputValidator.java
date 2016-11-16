package br.com.luxfacta.rest;

import java.time.LocalDate;

import org.joni.exception.ValueException;
import org.springframework.stereotype.Service;

@Service
public class CurrencyInputValidator {
	
	public void validateDate(String date){
		if(!date.matches("\\d{4}-\\d{2}-\\d{2}"))
			throw new ValueException("");
		converDate(date);
	}
	public LocalDate converDate(String possibleDate){
		return LocalDate.parse(possibleDate);
	}
	
}
