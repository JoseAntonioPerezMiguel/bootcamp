package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.core.contracts.repositories.RepositoryWithProjections;
import com.example.domains.entities.Actor;
import com.example.domains.entities.models.ActorDTO;

public interface ActorRepository extends JpaRepository<Actor, Integer>, RepositoryWithProjections{
	List<Actor> findTop5ByLastNameStartingWithOrderByFirstNameDesc(String prefix);

	List<Actor> findTop5ByLastNameStartingWith(String prefix, Sort orderBy);

	List<Actor> findByActorIdGreaterThanEqual(int actorId);

	@Query(value = "from Actor a where a.actorId >= ?1")
	List<Actor> findByJPQL(int actorId);

	@Query(value = "SELECT * FROM actor WHERE actor_id >= ?1", nativeQuery = true)
	List<Actor> findBySQL(int id);

	List<ActorDTO> readByActorIdGreaterThanEqual(int actorId);

	<T> List<T> findByActorIdGreaterThanEqual(int actorId, Class<T> proyection);
}
