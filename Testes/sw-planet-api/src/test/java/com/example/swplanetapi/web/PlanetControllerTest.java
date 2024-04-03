package com.example.swplanetapi.web;

import static com.example.swplanetapi.common.PlanetConstants.PLANET;
import static com.example.swplanetapi.common.PlanetConstants.PLANETS;
import static com.example.swplanetapi.common.PlanetConstants.TATOOINE;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.swplanetapi.domain.Planet;
import com.example.swplanetapi.domain.PlanetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PlanetController.class)
public class PlanetControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  // MOCK do PlanetService
  @MockBean
  private PlanetService planetService;

  // Teste para verificar a criação de um planeta com dados válidos
  @Test
  public void createPlanet_WithValidData_ReturnsCreated() throws Exception {
    when(planetService.create(PLANET)).thenReturn(PLANET);

    mockMvc
        .perform(
            post("/planets").content(objectMapper.writeValueAsString(PLANET))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$").value(PLANET));
  }

  // Teste para verificar a criação de um planeta com dados inválidos
  @Test
  public void createPlanet_WithInvalidData_ReturnsBadRequest() throws Exception {
    Planet emptyPlanet = new Planet();
    Planet invalidPlanet = new Planet("", "", "");

    mockMvc
        .perform(
            post("/planets").content(objectMapper.writeValueAsString(emptyPlanet))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity());
    mockMvc
        .perform(
            post("/planets").content(objectMapper.writeValueAsString(invalidPlanet))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity());
  }

  // Teste para verificar a criação de um planeta com nome existente
  @Test
  public void createPlanet_WithExistingName_ReturnsConflict() throws Exception {
    when(planetService.create(any())).thenThrow(DataIntegrityViolationException.class);

    mockMvc
        .perform(
            post("/planets").content(objectMapper.writeValueAsString(PLANET))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict());
  }

  // Teste para verificar a obtenção de um planeta pelo ID existente
  @Test
  public void getPlanet_ByExistingId_ReturnsPlanet() throws Exception {
    when(planetService.get(1L)).thenReturn(Optional.of(PLANET));

    mockMvc
        .perform(
            get("/planets/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(PLANET));
  }

  // Teste para verificar a obtenção de um planeta pelo ID inexistente
  @Test
  public void getPlanet_ByUnexistingId_ReturnsNotFound() throws Exception {
    mockMvc.perform(get("/planets/1"))
        .andExpect(status().isNotFound());
  }

  // Teste para verificar a obtenção de um planeta pelo nome existente
  @Test
  public void getPlanet_ByExistingName_ReturnsPlanet() throws Exception {
    when(planetService.getByName(PLANET.getName())).thenReturn(Optional.of(PLANET));

    mockMvc
        .perform(
            get("/planets/name/" + PLANET.getName()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(PLANET));
  }

  // Teste para verificar a obtenção de um planeta pelo nome inexistente
  @Test
  public void getPlanet_ByUnexistingName_ReturnsNotFound() throws Exception {
    mockMvc.perform(get("/planets/name/1"))
        .andExpect(status().isNotFound());
  }

  // Teste para verificar a listagem de planetas com filtro
  @Test
  public void listPlanets_ReturnsFilteredPlanets() throws Exception {
    when(planetService.list(null, null)).thenReturn(PLANETS);
    when(planetService.list(TATOOINE.getTerrain(), TATOOINE.getClimate())).thenReturn(List.of(TATOOINE));

    mockMvc
        .perform(
            get("/planets"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)));

    mockMvc
        .perform(
            get("/planets?" + String.format("terrain=%s&climate=%s", TATOOINE.getTerrain(), TATOOINE.getClimate())))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0]").value(TATOOINE));
  }

  // Teste para verificar a listagem de planetas sem filtro
  @Test
  public void listPlanets_ReturnsNoPlanets() throws Exception {
    when(planetService.list(null, null)).thenReturn(Collections.emptyList());

    mockMvc
        .perform(
            get("/planets"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  public void removePlanet_WithExistingId_ReturnsNoContent() throws Exception {
    mockMvc.perform(delete("/planets/1"))
        .andExpect(status().isNoContent());
  }

  @Test
  public void removePlanet_WithUnexistingId_ReturnsNotFound() throws Exception {
    final Long planetId = 1L;
    doThrow(new EmptyResultDataAccessException(1)).when(planetService).remove(planetId);

    mockMvc.perform(delete("/planets/" + planetId))
        .andExpect(status().isNotFound());
  }
}
