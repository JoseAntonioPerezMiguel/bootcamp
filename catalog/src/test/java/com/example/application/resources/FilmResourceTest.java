package com.example.application.resources;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.core.contracts.services.FilmService;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.example.domains.entities.models.FilmDTO;
import com.example.domains.entities.models.FilmShort;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Value;

@WebMvcTest(FilmResource.class)
class FilmResourceTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private FilmService srv;

    @Autowired
    ObjectMapper objectMapper;
    
    List<Film> films;
//    int filmId, String title, Short releaseYear, byte rentalDuration, BigDecimal rentalRate,
//	BigDecimal replacementCost, Language language
    @BeforeEach
    void setUp() throws Exception {
        films = new ArrayList<>(
                Arrays.asList(new Film(1, "Film1", (short) 2001, (byte)7, new BigDecimal(7.1), new BigDecimal(2.4), new Language(1)),
                		new Film(2, "Film2", (short) 2002, (byte)8, new BigDecimal(7.2), new BigDecimal(2.4), new Language(1)),
                		new Film(3, "Film3", (short) 2003, (byte)9, new BigDecimal(7.3), new BigDecimal(2.4), new Language(1))
                              ));
    }

    @AfterEach
    void tearDown() throws Exception {
    }
    
    @Value
    static class FilmShortMock implements FilmShort {
        int id;
        String title;
    }
    
    @Test
    void testGetAllString() throws Exception {
        List<FilmDTO> list = films.stream()
                .map(FilmDTO::from)
                .toList();
        when(srv.getByProjection(FilmDTO.class)).thenReturn(list);
        
        mockMvc.perform(get("/api/films/v1?mode=large").accept(MediaType.APPLICATION_JSON))
            .andExpectAll(
                    status().isOk(), 
                    content().contentType("application/json"),
                    jsonPath("$.size()").value(3)
                    );
    }

    @Test
    void testGetById() throws Exception {
        int id = 1;
        var ele = films.get(0);
        when(srv.getOne(id)).thenReturn(Optional.of(ele));
        
        mockMvc.perform(get("/api/films/v1/{id}", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.title").value(ele.getTitle()))
            .andDo(print());
    }

    @Test
    void testGetByCategory() throws Exception {
        int categoryId = 1;
        List<FilmDTO> list = films.stream().map(FilmDTO::from).toList();
        
        when(srv.getFilmsByCategory(categoryId)).thenReturn(films);
        
        mockMvc.perform(get("/api/films/v1/category/{id}", categoryId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(3))
            .andDo(print());
    }
    
    @Test
    void testGetByLanguage() throws Exception {
        int languageId = 1;
        List<FilmDTO> list = films.stream().map(FilmDTO::from).toList();
        
        when(srv.getFilmsByLanguage(languageId)).thenReturn(films);
        
        mockMvc.perform(get("/api/films/v1/language/{id}", languageId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(3))
            .andDo(print());
    }
    
    @Test
    void testCreate() throws Exception {
        int id = 1;
        var ele = films.get(0);
        when(srv.add(any(Film.class))).thenReturn(ele);
        
        mockMvc.perform(post("/api/films/v1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(FilmDTO.from(ele)))
            )
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/api/films/v1/1"))
            .andDo(print());
    }

    @Test
    void testUpdate() throws Exception {
        int id = 1;
        var ele = films.get(0);
        
        mockMvc.perform(put("/api/films/v1/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(FilmDTO.from(ele)))
            )
            .andExpect(status().isNoContent())
            .andDo(print());
        
        verify(srv).modify(any(Film.class));
    }

    @Test
    void testDelete() throws Exception {
        int id = 1;
        
        mockMvc.perform(delete("/api/films/v1/{id}", id))
            .andExpect(status().isNoContent())
            .andDo(print());
        
        verify(srv).deleteById(id);
    }
}
