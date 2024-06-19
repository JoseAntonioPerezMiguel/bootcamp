package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Pruebas de la clase calculadora")
class CalculadoraTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Nested
	class Add {
		
		@Nested
		class OK {
			@Test
			@DisplayName("Suma dos enteros")
			void testAdd() {
				var result = Calculadora.add(1, 2);
				assertEquals(3, result);
			}
			
			@Test
			@DisplayName("Suma dos reales")
			void testAdd2() {
				var result = Calculadora.add(1, -0.9);
				assertEquals(0.1, result);
			}			
		}
		
		@Nested
		class KO {
			
		}
	}
	
	@Nested
	@DisplayName("Método Div")
	class Div {		
		@Nested
		class OK {
			@Test
			@DisplayName("Divide dos enteros")
			void testDivInt() {
				var result = Calculadora.div(3, 2);
				assertEquals(1, result);
			}
			
			@Test
			@DisplayName("Divide dos reales")
			void testDivReal() {
				var result = Calculadora.div(3.0, 2.0);
				assertEquals(1.5, result);
			}			
		}
		
		@Nested
		class KO {
			@Test
			@DisplayName("Divide entre 0")
			void testDivRealKO() {
				assertThrows(ArithmeticException.class, () -> Calculadora.div(3.0, 0));
			}
		}
	}
}
