package com.example.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.core.contracts.repositories.RepositoryWithProjections;
import com.example.domains.entities.FilmActor;
import com.example.domains.entities.FilmActorPK;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorPK>, RepositoryWithProjections{

}
