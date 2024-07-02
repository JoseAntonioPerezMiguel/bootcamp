package com.example.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.core.contracts.repositories.RepositoryWithProjections;
import com.example.domains.entities.Film;
import java.util.List;


public interface FilmRepository extends JpaRepository<Film, Integer>, RepositoryWithProjections{
	
	@Query(value = "SELECT f.*"
			+ "FROM film f JOIN film_category fc ON f.film_id = fc.film_id WHERE fc.category_id = ?1", nativeQuery = true)
//	@Query("from Film f where f.filmCategories any category.categoryId = ?1")
	List<Film> findByCategory(int categoryId);
}
