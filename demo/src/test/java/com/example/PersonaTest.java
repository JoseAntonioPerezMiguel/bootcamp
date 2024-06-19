package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Pruebas de la clase Persona")
class PersonaTest {

	@Nested
	@DisplayName("Proceso de instanciación")
	class Create {
		@Nested
		class OK {
			@Test
			void soloNombre() {
				var persona = new Persona(1, "Muis");
//				var persona = new Persona(2, "Nergio", "Sosti");
				assertNotNull(persona);
				assertAll("Persona", 
						() -> assertEquals(1, persona.getId(), "id"),
						() -> assertEquals("Muis", persona.getNombre(), "nombre"),
						() -> assertTrue(persona.getApellidos().isEmpty(), "apellidos")
				);
			}
		}

		@Nested
		class KO {
//			@Test
//			void test() {
//				fail("Not yet implemented");
//			}
		}
	}
}
