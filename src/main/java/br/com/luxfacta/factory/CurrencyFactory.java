package br.com.luxfacta.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.luxfacta.facade.CurrencyFacade;
import br.com.luxfacta.validator.CurrencyInputValidator;

@Configuration
public class CurrencyFactory {
	@Bean
	public CurrencyFacade CurrencyFacade(){
		return new CurrencyFacade();
	}
	@Bean
	public CurrencyInputValidator CurrencyInputValidator(){
		return new CurrencyInputValidator();
	}
}
