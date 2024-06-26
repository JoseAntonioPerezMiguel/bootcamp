package com.example.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.core.contracts.repositories.RepositoryWithProjections;
import com.example.domains.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>, RepositoryWithProjections{

}
