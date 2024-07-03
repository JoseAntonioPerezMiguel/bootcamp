package com.example.domains.entities.models;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.PersistenceCreator;

import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmDTO implements Serializable {
	
	@JsonProperty("id")
	private int filmId;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("release_year")
	private Short releaseYear;
	
	@JsonProperty("rental_duration")
	private byte rentalDuration;
	
	@JsonProperty("rental_rate")
	private BigDecimal rentalRate;
	
	@JsonProperty("replacement_cost")
	private BigDecimal replacementCost;
	
	@JsonProperty("language_id")
	private int languageId;
	
	public static FilmDTO from(Film film) {
		return new FilmDTO(film.getFilmId(), film.getTitle(), film.getReleaseYear(), film.getRentalDuration(),
				film.getRentalRate(),  film.getReplacementCost(), film.getLanguage());
	}
	
	public static Film from(FilmDTO filmDTO) {
		return new Film(filmDTO.getFilmId(),  filmDTO.getTitle(), filmDTO.getReleaseYear(), 
				filmDTO.getRentalDuration(), filmDTO.getRentalRate(), filmDTO.getReplacementCost(), new Language(filmDTO.languageId));
	}

	@PersistenceCreator
	public FilmDTO(int filmId, String title, Short releaseYear, byte rentalDuration, BigDecimal rentalRate,
			BigDecimal replacementCost, Language language) {
		this.filmId = filmId;
		this.title = title;
		this.releaseYear = releaseYear;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.replacementCost = replacementCost;
		this.languageId = language.getLanguageId();
	}
	
	
}
