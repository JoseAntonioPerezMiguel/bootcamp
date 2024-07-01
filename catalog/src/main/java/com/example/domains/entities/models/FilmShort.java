package com.example.domains.entities.models;

import org.springframework.beans.factory.annotation.Value;

public interface FilmShort {

	@Value("#{target.ActorId}")
	int getId();
	
	
	@Value("#{target.title}")
	String getTitle();
	
}
