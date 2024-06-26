package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.core.contracts.services.ActorService;
import com.example.core.contracts.services.CategoryService;
import com.example.core.contracts.services.FilmService;
import com.example.core.contracts.services.LanguageService;
import com.example.domains.entities.Actor;

@SpringBootTest
class ActorRepositoryRealTest {
	@Autowired
	ActorRepository dao;

	@Test
	void test() {
		assertThat(dao.findAll().size()).isGreaterThan(200);
	}

	@Test
	void findTop5ByLastNameStartingWithOrderByFirstNameDescTest() {
		assertThat(dao.findTop5ByLastNameStartingWithOrderByFirstNameDesc("P").size()).isEqualTo(5);
	}

	@Test
	void findBySQLTest() {
		assertThat(dao.findBySQL(1).size()).isGreaterThan(0);
	}

}