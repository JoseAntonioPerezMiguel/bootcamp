package com.example.application.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.core.contracts.services.ActorService;
import com.example.domains.entities.models.ActorDTO;
import com.example.domains.entities.models.ActorShort;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/actors/v1")
public class ActorResource {
	
	@Autowired
	private ActorService srv;

	public ActorResource(ActorService srv) {
		this.srv = srv;
	}

	@GetMapping
	public List<ActorShort> getAll() {
		return srv.getByProjection(ActorShort.class);
	}
	
	@GetMapping(path = "/{id}")
	public ActorDTO getById(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if(item.isEmpty()) {
			throw new NotFoundException();
		}
		return ActorDTO.from(item.get());
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ActorDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
	var newItem = srv.add(ActorDTO.from(item));
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getActorId()).toUri();
	return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody ActorDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
		if(id != item.getActorId()) {
			throw new BadRequestException("No coinciden los identificadores.");
		}
		srv.modify(ActorDTO.from(item));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}
