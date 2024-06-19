package com.example.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;

//import com.example.ioc.SaludaImpl.SaludaEvent;

@Configuration
public class MyConfig {
	
	@Value("${app.cont.init:1}")
	int contInit;
	
	@Bean
	int contInit() {
		return contInit;
	}
	
	@Bean
	@Scope("prototype")
	Entorno entorno(@Value("${app.cont.init:1}") int contInt) {
		return new EntornoImpl(contInt);
	}

	@EventListener
	void trataEvento(SaludaImpl.SaludaEvent ev) {
		System.err.println("Evento -> " + ev.tipo() + " -> " + ev.destinatario());
	}
}
