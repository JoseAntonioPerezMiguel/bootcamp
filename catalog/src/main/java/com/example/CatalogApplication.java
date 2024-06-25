package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.core.contracts.services.FilmService;
import com.example.domains.contracts.repositories.FilmRepository;
import com.example.domains.entities.Film;

@SpringBootApplication
public class CatalogApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}
	
	@Autowired
	FilmService srv;

	@Autowired
	FilmRepository dao;
	
	@Override
	public void run(String... args) throws Exception{
		System.err.println("Aplicación arrancada");
		srv.getByProjection(Film.class).forEach(System.out::println);
	}
}
