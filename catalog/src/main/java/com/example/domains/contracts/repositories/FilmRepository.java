package com.example.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.core.contracts.repositories.RepositoryWithProjections;
import com.example.domains.entities.Film;
import java.util.List;


public interface FilmRepository extends JpaRepository<Film, Integer>, RepositoryWithProjections{
	
	@Query(value = "SELECT f.* FROM film f JOIN film_category fc ON f.film_id = fc.film_id WHERE fc.category_id = ?1", nativeQuery = true)
	List<Film> findByCategory(int categoryId);

	@Query("from Film f join f.language l where l.languageId = ?1")
	List<Film> findByLanguage(int id);
}
