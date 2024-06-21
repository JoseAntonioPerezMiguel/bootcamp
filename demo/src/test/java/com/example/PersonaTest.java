package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.core.test.Smoke;

@DisplayName("Pruebas de la clase Persona")
class PersonaTest {

	@Nested
	@DisplayName("Proceso de instanciación")
	class Create {
		@Nested
		class OK {
			@Test
			@Smoke
			void soloNombre() {
				var persona = new Persona(1, "Muis");
//				var persona = new Persona(2, "Nergio", "Sosti");
				assertNotNull(persona);
				assertAll("Persona", () -> assertEquals(1, persona.getId(), "id"),
						() -> assertEquals("Muis", persona.getNombre(), "nombre"),
						() -> assertTrue(persona.getApellidos().isEmpty(), "apellidos"));
			}

			@ParameterizedTest(name = "{0} {1}")
			@CsvSource(value = { "1,Muis", "2,'Nergio, Sosti'", "3, Nergio, Sosti" })
			void soloNombre(int id, String nombre) {
				var persona = new Persona(id, nombre);
				assertNotNull(persona);
				assertAll("Persona", () -> assertEquals(id, persona.getId(), "id"),
						() -> assertEquals(nombre, persona.getNombre(), "nombre"),
						() -> assertTrue(persona.getApellidos().isEmpty(), "apellidos"));
			}

			@ParameterizedTest(name = "{0} {1}")
			@CsvSource(value = { "1,Muis", "2,'Nergio, Sosti'", "3, Nergio, Sosti" })
			@Disabled
			void soloNombre(ArgumentsAccessor args) {
				var persona = args.size() == 3 ? new Persona(args.getInteger(0), args.getString(1), args.getString(2))
						: new Persona(args.getInteger(0), args.getString(1));
				assertNotNull(persona);
				assertAll("Persona", () -> assertEquals(args.getInteger(0), persona.getId(), "id"),
						() -> assertEquals(args.getString(1), persona.getNombre(), "nombre"),
						() -> assertTrue(args.size() == 3 ? persona.getApellidos().isPresent()
								: persona.getApellidos().isEmpty(), "apellidos"));
//				assumeFalse(true, "falta los apellidos");
			}
		}

		@Nested
		class KO {
			@ParameterizedTest(name = "{0} {1}")
			@CsvSource(value = { "3, ''", "5, '      '" })
			void soloNombre(int id, String nombre) {
				assertThrows(Exception.class, () -> new Persona(id, nombre));
			}
		}

		@Nested
		@DisplayName("UpperCaseName in PersonService")
		class UpperCaseName {
			
			@Nested
			class OK {
				@Test
				void setUpperCaseNameService() {
					Persona person = new Persona(1, "Pepito", "Grillo");
					var dao = mock(PersonaRepository.class);
					when(dao.getOne(anyInt())).thenReturn(Optional.of(person));
					var service = new PersonService(dao);
					service.setUpperCaseName(1);
					verify(dao).modify(person);
				}
			}
			
			@Nested
			class KO {
				@Test
				void setUpperCaseNameService() {
					var dao = mock(PersonaRepository.class);
					when(dao.getOne(anyInt())).thenReturn(Optional.empty());
					var service = new PersonService(dao);
					assertThrows(IllegalArgumentException.class, () -> service.setUpperCaseName(1));
				}
			}
		}
	}
}
