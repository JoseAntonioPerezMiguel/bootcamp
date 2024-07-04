package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.core.contracts.repositories.ProjectionsAndSpecificationJpaRepository;
import com.example.domains.entities.Film;

@RepositoryRestResource(path="films", itemResourceRel="film", collectionResourceRel="films")
public interface FilmRepository extends ProjectionsAndSpecificationJpaRepository<Film, Integer>{
	@RestResource(path = "by-title")
	List<Film> findByTitleStartingWith(String nombre);
}
