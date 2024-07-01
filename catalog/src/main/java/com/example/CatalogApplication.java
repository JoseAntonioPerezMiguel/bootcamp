package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.core.contracts.services.FilmService;
import com.example.domains.entities.models.FilmDTO;

@SpringBootApplication
public class CatalogApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

	@Autowired
	FilmService filmSrv;

	@Override
	public void run(String... args) throws Exception {
		filmSrv.getByProjection(FilmDTO.class).forEach(System.out::println);
	}
}
