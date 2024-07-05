package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.core.contracts.services.FilmService;
import com.example.domains.entities.models.FilmDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class CatalogApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

//	@Autowired
//	FilmService filmSrv;

	@Override
	public void run(String... args) throws Exception {
//		ObjectMapper om = new ObjectMapper();
//		filmSrv.getByProjection(FilmDTO.class).forEach(System.out::println);
//		System.out.println(om.writeValueAsString(filmSrv.getByProjection(FilmDTO.class).getFirst()));
//		var dto = new FilmDTO()
	}
}
