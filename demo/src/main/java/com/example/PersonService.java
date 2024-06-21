package com.example;

public class PersonService {
	private PersonaRepository dao;

	public PersonService(PersonaRepository dao) {
		super();
		this.dao = dao;
	}
	
	public void setUpperCaseName(int id) {
		var item = dao.getOne(id);
		if(item.isEmpty()) {
			throw new IllegalArgumentException("Id no encontrado");
		}
		var p = item.get();
		p.setNombre(p.getNombre().toUpperCase());
		dao.modify(p);
	}
}
