package com.example.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.core.contracts.repositories.RepositoryWithProjections;
import com.example.domains.entities.FilmCategory;
import com.example.domains.entities.FilmCategoryPK;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryPK>, RepositoryWithProjections{

}
