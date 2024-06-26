package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}
/*
	@Autowired
	FilmService filmSrv;

	@Autowired
	ActorService actorSrv;

	@Autowired
	CategoryService categorySrv;

	@Autowired
	LanguageService languageSrv;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.err.println("Aplicación arrancada");
		filmSrv.getByProjection(PageRequest.of(0, 1, Sort.by("FilmId")), Film.class)
			.stream()
			.map(f -> new FilmDTO(f.getFilmId(), f.getTitle(), f.getReleaseYear(), f.getFilmCategories().stream()
						.map(fc -> fc.getCategory().getName()).toList(), 
					f.getFilmActors().stream()
						.map(fa -> fa.getActor().getFirstName() + " " + fa.getActor().getLastName()).toList()))
			.forEach(System.out::println);
		
		Film film = filmSrv.getOne(1).get();
		film.addActor(actorSrv.getOne(26).get());
		film.addCategory(categorySrv.getOne(2).get());
		filmSrv.modify(film);
		
		filmSrv.getByProjection(PageRequest.of(0, 1, Sort.by("FilmId")), Film.class)
		.stream()
		.map(f -> new FilmDTO(f.getFilmId(), f.getTitle(), f.getReleaseYear(), f.getFilmCategories().stream()
					.map(fc -> fc.getCategory().getName()).toList(), 
				f.getFilmActors().stream()
					.map(fa -> fa.getActor().getFirstName() + " " + fa.getActor().getLastName()).toList()))
		.forEach(System.out::println);
		
		film.removeCategory(2);
		film.removeActor(26);
		filmSrv.modify(film);
		
		filmSrv.getByProjection(PageRequest.of(0, 1, Sort.by("FilmId")), Film.class)
		.stream()
		.map(f -> new FilmDTO(f.getFilmId(), f.getTitle(), f.getReleaseYear(), f.getFilmCategories().stream()
					.map(fc -> fc.getCategory().getName()).toList(), 
				f.getFilmActors().stream()
					.map(fa -> fa.getActor().getFirstName() + " " + fa.getActor().getLastName()).toList()))
		.forEach(System.out::println);
		
		actorSrv.getByProjection(PageRequest.of(0, 5, Sort.by("ActorId")), ActorDTO.class).forEach(System.out::println);
		categorySrv.getByProjection(PageRequest.of(0, 5, Sort.by("CategoryId")), CategoryDTO.class)
				.forEach(System.out::println);
		languageSrv.getByProjection(PageRequest.of(0, 5, Sort.by("LanguageId")), LanguageDTO.class)
				.forEach(System.out::println);
	}*/
}
