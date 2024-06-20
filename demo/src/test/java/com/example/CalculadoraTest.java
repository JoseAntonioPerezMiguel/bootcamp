package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Pruebas de la clase Calculadora")
@TestMethodOrder(MethodOrderer.class)
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
			@RepeatedTest(value= 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
			void testAdd() {
				assertEquals(3, Calculadora.add(1, 2));
				assertEquals(3, Calculadora.add(4, -1), "caso 4,-1");
				assertEquals(-2, Calculadora.add(-1, -1), "caso -1,-1");
				assertEquals(0, Calculadora.add(0, 0), "caso 0,0");
			}
			
			@ParameterizedTest(name = "Caso {index}: {0} + {1} = {2}")
			@DisplayName("Suma dos enteros")
			@CsvSource(value = {"1,2,3", "3,-1,2", "-1,2,1", "-2,-3,-5", "0.1,0.2,0.3"})
			void testAdd(double operando1, double operando2, double resultado) {
				assertEquals(resultado, Calculadora.add(operando1, operando2));
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
			@Tag("smoke")
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
