package com.example.core.contracts.services;

import com.example.domains.core.contracts.services.ProjectionDomainService;
import com.example.domains.entities.Actor;

public interface ActorService extends ProjectionDomainService<Actor, Integer>{
	void giveAwards();
}
