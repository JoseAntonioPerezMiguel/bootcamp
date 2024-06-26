package com.example.ioc;

import java.util.Optional;

//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.NonNull;

@Component("saludaEs")
//@Qualifier("es")
@Scope("prototype")
@Profile("es")
public class SaludaImpl implements Saluda {
	
	public static record SaludaEvent(String tipo, String destinatario) {	}

	private ApplicationEventPublisher publisher;
	
	Entorno entorno;

	public SaludaImpl(Entorno entorno, ApplicationEventPublisher publisher) {
		this.entorno = entorno;
		this.publisher = publisher;
	}
	
	protected void onSaluda(@NonNull String tipo, @NonNull String destinatario) {
		publisher.publishEvent(new SaludaEvent(tipo, destinatario));
	}

	@Override
	public void saluda(@NonNull String nombre) {
		entorno.write("Hola " + nombre.toUpperCase());
		onSaluda("saluda", nombre);
	}
	
	public void saluda() {
		entorno.write("Hola");
		onSaluda("saluda", "Sin nombre");
	}

	@Override
	public int getContador() {
		return entorno.getContador();
	}

	public Optional<Entorno> getEntorno() {
		return Optional.ofNullable(entorno);
	}
}
