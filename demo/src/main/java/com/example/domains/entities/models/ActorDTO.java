package com.example.domains.entities.models;

import com.example.domains.entities.Actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ActorDTO {
	private int actorId;
	private String firstName;
	private String lastName;
	
	public static ActorDTO from(Actor actor) {
		return new ActorDTO(actor.getActorId(), actor.getFirstName(), actor.getLastName());
	}
	
	public static Actor from(ActorDTO actorDTO) {
		return new Actor(actorDTO.getActorId(), actorDTO.getFirstName(), actorDTO.getLastName());
	}
}
