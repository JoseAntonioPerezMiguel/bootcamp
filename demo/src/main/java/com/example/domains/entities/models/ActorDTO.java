package com.example.domains.entities.models;

import java.io.Serializable;

import com.example.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActorDTO implements Serializable {
	@JsonProperty("id")
	private int actorId;
	
	@JsonProperty("name")
	@NotBlank
	@Size(max = 5)
	private String firstName;
	
	@JsonProperty("lastname")
	private String lastName;
	
	public static ActorDTO from(Actor actor) {
		return new ActorDTO(actor.getActorId(), actor.getFirstName(), actor.getLastName());
	}
	
	public static Actor from(ActorDTO actorDTO) {
		return new Actor(actorDTO.getActorId(), actorDTO.getFirstName(), actorDTO.getLastName());
	}
}
