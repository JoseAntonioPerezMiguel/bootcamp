package com.example.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.core.contracts.repositories.RepositoryWithProjections;
import com.example.domains.entities.Film;

public interface LanguageRepository extends JpaRepository<Film, Integer>, RepositoryWithProjections{

}
