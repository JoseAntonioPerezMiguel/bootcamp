package com.example.application.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.application.resources.ActorResource.Peli;
import com.example.core.contracts.services.FilmService;
import com.example.domains.entities.Film;
import com.example.domains.entities.models.FilmDTO;
import com.example.domains.entities.models.FilmShort;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/films/v1")
public class FilmResource {

	@Autowired
	private FilmService srv;

	public FilmResource(FilmService srv) {
		this.srv = srv;
	}

	@GetMapping
	public List getAll(@RequestParam(required = false, defaultValue = "large") String mode) {
		if ("short".equals(mode)) {
			return srv.getByProjection(FilmShort.class);
		} else {
			return srv.getByProjection(FilmDTO.class);
		}
	}

	@GetMapping(params = "page")
	public Page<FilmShort> getAll(Pageable page) {
		return srv.getByProjection(page, FilmShort.class);
	}

	@GetMapping(path = "/{id}")
	public FilmDTO getById(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty()) {
			throw new NotFoundException();
		}
		return FilmDTO.from(item.get());
	}
	
	@GetMapping(path = "/category/{id}")
	public List<FilmDTO> getByCategory(@PathVariable int id) throws NotFoundException {
		var items = srv.getFilmsByCategory(id);
		return items.stream().map(f -> FilmDTO.from(f)).toList();
	}
	
	@GetMapping(path = "/language/{id}")
	public List<FilmDTO> getByLanguage(@PathVariable int id) throws NotFoundException {
		var items = srv.getFilmsByLanguage(id);
		return items.stream().map(f -> FilmDTO.from(f)).toList();
	}
	
	record Actor(int id, String firstName, String lastName) {
		
	}
	@GetMapping(path = "/{id}/actors")
	@Transactional
	public List<Actor> getActors(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty()) {
			throw new NotFoundException();
		}
		return item.get().getFilmActors().stream()
				.map(fa -> new Actor(fa.getActor().getActorId(), fa.getActor().getFirstName(), fa.getActor().getLastName())).toList();
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody FilmDTO item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(FilmDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getFilmId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody FilmDTO item)
			throws BadRequestException, NotFoundException, InvalidDataException {
		if (id != item.getFilmId()) {
			throw new BadRequestException("No coinciden los identificadores.");
		}
		srv.modify(FilmDTO.from(item));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}
