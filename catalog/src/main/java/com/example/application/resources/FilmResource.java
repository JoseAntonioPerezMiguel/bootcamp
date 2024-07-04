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

import com.example.core.contracts.services.FilmService;
import com.example.domains.entities.models.FilmDTO;
import com.example.domains.entities.models.FilmShort;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/films/v1")
@Tag(name = "Film Resource", description = "Controller for managing films")
public class FilmResource {

	@Autowired
	private FilmService srv;

	public FilmResource(FilmService srv) {
		this.srv = srv;
	}
	@Operation(summary = "Get all films", description = "Retrieves all films in either short or full detail based on the mode parameter")
	@GetMapping
	public List getAll(@RequestParam(required = false, defaultValue = "large") String mode) {
		if ("short".equals(mode)) {
			return srv.getByProjection(FilmShort.class);
		} else {
			return srv.getByProjection(FilmDTO.class);
		}
	}

	@Operation(summary = "Get all films with pagination", description = "Retrieves all films in short detail with pagination")
    @GetMapping(params = "page")
	public Page<FilmShort> getAll(Pageable page) {
		return srv.getByProjection(page, FilmShort.class);
	}

	@Operation(summary = "Get a film by ID", description = "Retrieves a film by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Film found", content = @Content(schema = @Schema(implementation = FilmDTO.class))),
        @ApiResponse(responseCode = "404", description = "Film not found")
    })
	@GetMapping(path = "/{id}")
	public FilmDTO getById(@PathVariable @Parameter(description = "ID of the film to be retrieved") int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty()) {
			throw new NotFoundException();
		}
		return FilmDTO.from(item.get());
	}
	
	@Operation(summary = "Get films by category", description = "Retrieves films by category ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Films found", content = @Content(schema = @Schema(implementation = FilmDTO.class))),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
	@GetMapping(path = "/category/{id}")
	public List<FilmDTO> getByCategory(@PathVariable @Parameter(description = "ID of the category") int id) throws NotFoundException {
		var items = srv.getFilmsByCategory(id);
		return items.stream().map(f -> FilmDTO.from(f)).toList();
	}
	
	@Operation(summary = "Get films by language", description = "Retrieves films by language ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Films found", content = @Content(schema = @Schema(implementation = FilmDTO.class))),
        @ApiResponse(responseCode = "404", description = "Language not found")
    })
	@GetMapping(path = "/language/{id}")
	public List<FilmDTO> getByLanguage(@PathVariable @Parameter(description = "ID of the language") int id) throws NotFoundException {
		var items = srv.getFilmsByLanguage(id);
		return items.stream().map(f -> FilmDTO.from(f)).toList();
	}
	
	record Actor(int id, String firstName, String lastName) {
		
	}
	
	@Operation(summary = "Get actors of a film", description = "Retrieves the actors of a film by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Actors found", content = @Content(schema = @Schema(implementation = Actor.class))),
        @ApiResponse(responseCode = "404", description = "Film not found")
    })
	@GetMapping(path = "/{id}/actors")
	@Transactional
	public List<Actor> getActors(@PathVariable @Parameter(description = "ID of the film") int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty()) {
			throw new NotFoundException();
		}
		return item.get().getFilmActors().stream()
				.map(fa -> new Actor(fa.getActor().getActorId(), fa.getActor().getFirstName(), fa.getActor().getLastName())).toList();
	}

	@Operation(summary = "Create a new film", description = "Creates a new film")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Film created", content = @Content(schema = @Schema(implementation = URI.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "409", description = "Film already exists")
    })
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody  @Parameter(description = "Film to create") FilmDTO item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(FilmDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getFilmId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Operation(summary = "Update a film", description = "Updates an existing film by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Film updated"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Film not found")
    })
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable @Parameter(description = "ID of updated film") int id,
			@Valid @RequestBody @Parameter(description = "Updated film data") FilmDTO item)
			throws BadRequestException, NotFoundException, InvalidDataException {
		if (id != item.getFilmId()) {
			throw new BadRequestException("No coinciden los identificadores.");
		}
		srv.modify(FilmDTO.from(item));
	}

	@Operation(summary = "Delete a film", description = "Deletes a film by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Film deleted")
    })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @Parameter(description = "ID of the film to be deleted") int id) {
		srv.deleteById(id);
	}
}
