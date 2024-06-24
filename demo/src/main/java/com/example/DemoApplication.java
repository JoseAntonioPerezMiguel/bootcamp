package com.example;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.entities.Actor;
import com.example.ioc.Entorno;
import com.example.ioc.Rango;
import com.example.ioc.Saluda;
//import com.example.ioc.SaludaEnImpl2;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired
	ActorRepository dao;
	
	@Override
	public void run(String... args) throws Exception{
		System.err.println("Aplicación arrancada");
//		dao.findAll().forEach(System.out::println);
		var item = dao.findById(201);
		if(item.isEmpty()) {
			System.err.println("No encontrado");
		} else {
			var actor = item.get();
			actor.setFirstName(actor.getFirstName().toUpperCase());
			dao.save(actor);
		}
		dao.findAll().forEach(System.out::println);
	}
}
