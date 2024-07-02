package com.example.core.contracts.services;

import java.util.List;

import com.example.domains.core.contracts.services.ProjectionDomainService;
import com.example.domains.entities.Film;

public interface FilmService extends ProjectionDomainService<Film, Integer> {

	List<Film> getFilmsByCategory(int id);
}
