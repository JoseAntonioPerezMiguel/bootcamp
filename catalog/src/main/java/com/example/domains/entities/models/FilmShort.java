package com.example.domains.entities.models;

import org.springframework.beans.factory.annotation.Value;

public interface FilmShort {

	@Value("#{target.FilmId}")
	int getId();
	
	
	@Value("#{target.title}")
	String getTitle();
	
}
