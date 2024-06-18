package com.example.ioc;

import org.springframework.stereotype.Component;

//@Component
public class SaludaEnImpl2 implements Saluda {
	
	Entorno entorno;
	
	public SaludaEnImpl2(Entorno entorno) {
		this.entorno = entorno;
	}

	@Override
	public void saluda(String nombre)
	{
		entorno.write("Hello " + nombre);
	}
}
