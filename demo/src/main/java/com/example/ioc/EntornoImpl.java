package com.example.ioc;

//@Service
public class EntornoImpl implements Entorno {
	
	private int contador;
	
	public EntornoImpl(int contInit) {
		this.contador = contInit;
	}

	@Override
	public void write(String cad) {
		contador++;
		System.out.println(cad);
	}

	@Override
	public int getContador() {
		return contador;
	}	
}
