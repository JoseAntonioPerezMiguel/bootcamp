package com.example;

import java.util.Optional;

public class Persona {
	private int id;
	private String nombre;
	private String apellidos;

	public Persona(int id, String nombre) {
		this.id = id;
		setNombre(nombre);
	}
	
	public Persona(int id, String nombre, String apellidos) {
		this.id = id;
		setNombre(nombre);
		setApellidos(apellidos);
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Optional<String> getApellidos() {
		return Optional.ofNullable(apellidos);
	}
	
//	public String getApellidos() {
//		if(!hasApellidos()) throw new NullPointerException("No tengo apellidos");
//		return apellidos;
//	}
//	
//	public boolean hasApellidos() {
//		return apellidos != null;
//	}

	private void setId(int id) {
		this.id = id;
	}

	private void setNombre(String nombre) {
		if(nombre == null) throw new IllegalArgumentException("Falta el nombre");
		this.nombre = nombre;
	}

	private void setApellidos(String apellidos) {
		if(apellidos == null) throw new IllegalArgumentException("Faltan los apellidos");
		this.apellidos = apellidos;
	}
	
	private void clearApellidos() {
		this.apellidos = null;
	}
}