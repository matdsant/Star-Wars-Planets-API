package com.example.swplanetapi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static com.example.swplanetapi.common.PlanetConstants.PLANET;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = PlanetService.class)
public class PlanetServiceTest {
    @Autowired
    private PlanetService planetService;

    @MockBean 
    private PlanetRepository planetRepository;

    @Test
    public void createPlanet_WithValidData_ReturnsPlanet() {
        // AAA
        // Arrange - Arruma os dados e prepara o Stub
        when(planetRepository.save(PLANET)).thenReturn(PLANET);
        // Act - Agir na operação e executa
        Planet sut = planetService.create(PLANET);
        // Assert - Aferir se o sistema é o que se esperava
        assertThat(sut).isEqualTo(PLANET);
    }
}