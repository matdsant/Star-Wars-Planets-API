package com.example.swplanetapi.domain;

import static com.example.swplanetapi.common.PlanetConstants.INVALID_PLANET;
import static com.example.swplanetapi.common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = PlanetService.class)
public class PlanetServiceTest {
    //@Autowired
    @InjectMocks
    private PlanetService planetService;

    //@MockBean
    @Mock
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

    @Test
    public void createPlanet_WithInvalidData_ThrowsException() {
        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

        assertThatThrownBy(()->planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }

    // Exercise ONE
    @Test
    public void getPlanet_ByExistingId_ReturnsPlanet() {
        when(planetRepository.findById(1L)).thenReturn(Optional.of(PLANET));

        Optional<Planet> sut = planetService.get(1L);
        
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test
    public void getPlanet_ByUnexistingId_ReturnsEmpty() {
        when(planetRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Planet> sut = planetService.get(1L);

        assertThat(sut).isEmpty();
    }

    // Exercise TWO
    @Test
    public void getPlanet_ByExistingName_ReturnsPlanet() {
        when(planetRepository.findByName(PLANET.getName())).thenReturn(Optional.of(PLANET));

        Optional<Planet> sut = planetService.getByName(PLANET.getName());
        
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test
    public void getPlanet_ByUnexistingName_ReturnsEmpty() {
        final String name = "Unexisting Name";
        when(planetRepository.findByName(name)).thenReturn(Optional.empty());

        Optional<Planet> sut = planetService.getByName(name);

        assertThat(sut).isEmpty();
    }
}