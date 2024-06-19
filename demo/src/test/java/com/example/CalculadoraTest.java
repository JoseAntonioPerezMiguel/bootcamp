package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

	@Test
	void testAdd() {
		var result = Calculadora.add(1, 2);
		assertEquals(3, result);
	}
	
	@Test
	void testAdd2() {
		var result = Calculadora.add(1, -0.9);
		assertEquals(0.1, result);
	}

	@Test
	void testDivInt() {
		var result = Calculadora.div(3, 2);
		assertEquals(1, result);
	}
	
	@Test
	void testDivReal() {
		var result = Calculadora.div(3.0, 2.0);
		assertEquals(1.5, result);
	}
	
	@Test
	void testDivRealKO() {
		assertThrows(ArithmeticException.class, () -> Calculadora.div(3.0, 0));
	}

}
