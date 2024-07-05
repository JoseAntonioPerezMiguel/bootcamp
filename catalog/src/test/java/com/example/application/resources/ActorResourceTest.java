package com.example.application.resources;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import com.example.core.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.example.domains.entities.models.ActorDTO;
import com.example.domains.entities.models.ActorShort;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Value;

@WebMvcTest(ActorResource.class)
class ActorResourceTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ActorService srv;

    @Autowired
    ObjectMapper objectMapper;
    
    List<Actor> actors;
    
    @BeforeEach
    void setUp() throws Exception {
        actors = new ArrayList<>(
                Arrays.asList(new Actor(1, "Actor1", "LastName1"),
                              new Actor(2, "Actor2", "LastName2"),
                              new Actor(3, "Actor3", "LastName3")));
    }

    @AfterEach
    void tearDown() throws Exception {
    }
    
    @Value
    static class ActorShortMock implements ActorShort {
        int id;
        String nombre;
    }
    
    @Test
    void testGetAllString() throws Exception {
        List<ActorDTO> list = actors.stream()
                .map(ActorDTO::from)
                .toList();
        when(srv.getByProjection(ActorDTO.class)).thenReturn(list);
        
        mockMvc.perform(get("/api/actors/v1?mode=large").accept(MediaType.APPLICATION_JSON))
            .andExpectAll(
                    status().isOk(), 
                    content().contentType("application/json"),
                    jsonPath("$.size()").value(3)
                    );
    }

    @Test
    void testGetById() throws Exception {
        int id = 1;
        var ele = actors.get(0);
        when(srv.getOne(id)).thenReturn(Optional.of(ele));
        
        mockMvc.perform(get("/api/actors/v1/{id}", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.firstName").value(ele.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(ele.getLastName()))
            .andDo(print());
    }

    @Test
    void testGetById404() throws Exception {
        int id = 1;
        when(srv.getOne(id)).thenReturn(Optional.empty());
        
        mockMvc.perform(get("/api/actors/v1/{id}", id))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.title").value("Not Found"))
            .andDo(print());
    }

    @Test
    void testRetireActor() throws Exception {
        int id = 1;
        var ele = actors.get(0);
        when(srv.getOne(id)).thenReturn(Optional.of(ele));
        
        mockMvc.perform(put("/api/actors/v1/{id}/retirement", id))
            .andExpect(status().isAccepted())
            .andDo(print());
        
        verify(ele).retirement();
    }
    
    @Test
    void testCreate() throws Exception {
        int id = 1;
        var ele = actors.get(0);
        when(srv.add(any(Actor.class))).thenReturn(ele);
        
        mockMvc.perform(post("/api/actors/v1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(ActorDTO.from(ele)))
            )
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/api/actors/v1/1"))
            .andDo(print());
    }

    @Test
    void testUpdate() throws Exception {
        int id = 1;
        var ele = actors.get(0);
        
        mockMvc.perform(put("/api/actors/v1/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(ActorDTO.from(ele)))
            )
            .andExpect(status().isNoContent())
            .andDo(print());
        
        verify(srv).modify(any(Actor.class));
    }

    @Test
    void testDelete() throws Exception {
        int id = 1;
        
        mockMvc.perform(delete("/api/actors/v1/{id}", id))
            .andExpect(status().isNoContent())
            .andDo(print());
        
        verify(srv).deleteById(id);
    }
}