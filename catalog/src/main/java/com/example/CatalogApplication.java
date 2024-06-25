package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.core.contracts.services.ActorService;
import com.example.core.contracts.services.CategoryService;
import com.example.core.contracts.services.FilmService;
import com.example.core.contracts.services.LanguageService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;

@SpringBootApplication
public class CatalogApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}
	
	@Autowired
	FilmService filmSrv;
	
	@Autowired
	ActorService actorSrv;
	
	@Autowired
	CategoryService categorySrv;
	
	@Autowired
	LanguageService languageSrv;
	
	@Override
	public void run(String... args) throws Exception{
		System.err.println("Aplicación arrancada");
		filmSrv.getByProjection(PageRequest.of(0, 5, Sort.by("FilmId")), Film.class).forEach(System.out::println);
		actorSrv.getByProjection(PageRequest.of(0, 5, Sort.by("ActorId")), Actor.class).forEach(System.out::println);
		categorySrv.getByProjection(PageRequest.of(0, 5, Sort.by("CategoryId")), Category.class).forEach(System.out::println);
		languageSrv.getByProjection(PageRequest.of(0, 5, Sort.by("LanguageId")), Language.class).forEach(System.out::println);
	}
}
