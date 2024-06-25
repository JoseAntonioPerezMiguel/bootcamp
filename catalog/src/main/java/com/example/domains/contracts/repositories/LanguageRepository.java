package com.example.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.core.contracts.repositories.RepositoryWithProjections;
import com.example.domains.entities.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer>, RepositoryWithProjections{

}
