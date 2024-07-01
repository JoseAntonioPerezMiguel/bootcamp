package com.example.domains.entities.models;

import java.io.Serializable;
import java.util.List;

import com.example.domains.entities.Film;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilmDTO implements Serializable {
	@JsonProperty("id")
	private int filmId;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("release_year")
	private Short releaseYear;
	
	public static FilmDTO from(Film film) {
		return new FilmDTO(film.getFilmId(), film.getTitle(), film.getReleaseYear());
	}
	
	public static Film from(FilmDTO filmDTO) {
		return new Film(filmDTO.getFilmId(), filmDTO.getReleaseYear(), filmDTO.getTitle());
	}
}
