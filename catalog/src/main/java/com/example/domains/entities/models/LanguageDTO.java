package com.example.domains.entities.models;

import java.io.Serializable;

import com.example.domains.entities.Film;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class LanguageDTO implements Serializable {
	@JsonProperty("id")
	private int filmId;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("release_year")
	private Short releaseYear;
	
	public static LanguageDTO from(Film film) {
		return new LanguageDTO(film.getFilmId(), film.getTitle(), film.getReleaseYear());
	}
	
	public static Film from(LanguageDTO filmDTO) {
		return new Film(filmDTO.getFilmId(), filmDTO.getReleaseYear(), filmDTO.getTitle());
	}
}
