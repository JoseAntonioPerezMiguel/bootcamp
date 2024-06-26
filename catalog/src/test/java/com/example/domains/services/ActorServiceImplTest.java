package com.example.domains.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.entities.Actor;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;

class ActorServiceImplTest {

	@Mock
	private ActorRepository dao;

	@InjectMocks
	private ActorServiceImpl service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetByProjection() {
		List<String> actors = Arrays.asList("Actor1", "Actor2");
		when(dao.findAllBy(String.class)).thenReturn(actors);

		List<String> result = service.getByProjection(String.class);
		assertEquals(2, result.size());
		assertEquals("Actor1", result.get(0));
	}

	@Test
	void testGetAll() {
		List<Actor> actors = Arrays.asList(new Actor(1, "Actor1", "ApellidoActor1"),
				new Actor(2, "Actor2", "ApellidoActor2"));
		when(dao.findAll()).thenReturn(actors);

		List<Actor> result = service.getAll();
		assertEquals(actors, result);
	}

	@Test
	void testGetOne() {
		Optional<Actor> actor = Optional.of(new Actor(1, "Actor1", "ApellidoActor1"));
		when(dao.findById(1)).thenReturn(actor);

		Optional<Actor> result = service.getOne(1);
		assertEquals(actor.get(), result.get());
	}

	@ParameterizedTest
	@MethodSource("actorProvider")
	void testAdd(Actor actor, Class<? extends Exception> expectedException)
			throws DuplicateKeyException, InvalidDataException {
		if (expectedException == DuplicateKeyException.class && actor != null) {
			when(dao.existsById(actor.getActorId())).thenReturn(true);
		}

		if (actor != null && actor.getActorId() == 0 && !actor.isInvalid()) {
			when(dao.save(actor)).thenReturn(actor);
		}

		if (expectedException != null) {
			Exception exception = assertThrows(expectedException, () -> {
				service.add(actor);
			});
			if (expectedException == InvalidDataException.class) {
				if (actor == null) {
					assertEquals("No puede ser nulo", exception.getMessage());
				} else {
					assertEquals("ERRORES: firstName: Tiene que estar en mayusculas.", exception.getMessage());
				}
			} else if (expectedException == DuplicateKeyException.class) {
				assertEquals("Ya existe", exception.getMessage());
			}
		} else {
			when(dao.save(actor)).thenReturn(actor);
			Actor result = service.add(actor);
			assertEquals(actor, result);
		}
	}
	
	

	private static Stream<Arguments> actorProvider() {
		return Stream.of(Arguments.of(null, InvalidDataException.class),
				Arguments.of(new Actor(1, "123", "Apellido"), InvalidDataException.class),
				Arguments.of(new Actor(1, "NOMBRE", "Apellido"), null),
				Arguments.of(new Actor(1, "NOMBRE", "Apellido"), DuplicateKeyException.class));
	}
}
