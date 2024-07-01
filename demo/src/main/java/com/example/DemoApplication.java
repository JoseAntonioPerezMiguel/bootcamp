package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.core.contracts.services.ActorService;
import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.entities.models.ActorDTO;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired
	ActorService srv;

	@Autowired
	ActorRepository dao;
	
	public void run(String... args) throws Exception{
		System.err.println("Aplicación arrancada");
//		srv.getByProjection(ActorDTO.class).forEach(System.out::println);
	}

	/*
	@Override
	@Transactional
	public void run(String... args) throws Exception{
		System.err.println("Aplicación arrancada");
//		var item = dao.findById(201);
//		if(item.isEmpty()) {
//			System.err.println("No encontrado");
//		} else {
//			var actor = item.get();
//			actor.setFirstName(actor.getFirstName().toUpperCase());
//			dao.save(actor);
//		}
//		dao.deleteById(201);
//		dao.findAll().forEach(System.out::println);
//		dao.findTop5ByLastNameStartingWithOrderByFirstNameDesc("P").forEach(System.out::println);
//		dao.findTop5ByLastNameStartingWith("P", Sort.by("LastName").ascending()).forEach(System.out::println);
//		dao.findByActorIdGreaterThanEqual(198).forEach(System.out::println);
//		dao.findByJPQL(198).forEach(System.out::println);
//		dao.findBySQL(198).forEach(System.out::println);
//		dao.findAll((root, query, builder) -> builder.greaterThan(root.get("actorId"), 198)).forEach(System.out::println);
//		var item = dao.findAll();
//		if(item.isEmpty()) {
//			System.err.println("No encontrado");
//		} else {
//			var actor = item.get(0);
//			System.out.println(actor);
//			actor.getFilmActors().forEach(f -> System.out.println(f.getFilm().getTitle()));
//		}
//		var actor = new Actor(0, " ", null);
//		if(actor.isValid()) {
//			System.out.println(dao.save(actor));
//		} else {
//			actor.getErrors().forEach(System.out::println);
//		}
//		var actor = new ActorDTO(0, "FROM", "DTO");
//		dao.save(ActorDTO.from(actor));
//		dao.findAll().forEach(item -> System.out.println(ActorDTO.from(item)));
//		dao.readByActorIdGreaterThanEqual(200).forEach(System.out::println);
//		dao.queryByActorIdGreaterThanEqual(200).forEach(System.out::println);
//		dao.queryByActorIdGreaterThanEqual(200).forEach(item -> System.out.println(item.getId() + " " + item.getNombre()));
//		dao.findByActorIdGreaterThanEqual(200, ActorDTO.class).forEach(System.out::println);
//		dao.findByActorIdGreaterThanEqual(200, ActorShort.class).forEach(item -> System.out.println(item.getId() + " " + item.getNombre()));
//		dao.findAll(PageRequest.of(0, 10, Sort.by("ActorId"))).forEach(System.out::println);
		var serialize = new ObjectMapper();
		dao.findByActorIdGreaterThanEqual(200, Actor.class).forEach(item -> {
			try {
				System.out.println(serialize.writeValueAsString(item));
			} catch(JsonProcessingException e) {
				e.printStackTrace();
			}
		});
	}*/
}
