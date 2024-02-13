package com.example.swplanetapi.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class PlanetService {
    private PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }
    public Planet create(Planet planet) {
        return planetRepository.save(planet);
    }

    public Optional<Planet> get (Long id) {
        return planetRepository.findById(id);
    }
}