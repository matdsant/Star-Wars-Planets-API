package com.example.swplanetapi.web;

import org.springframework.web.bind.annotation.RestController;

import com.example.swplanetapi.domain.Planet;
import com.example.swplanetapi.domain.PlanetService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/planets")
public class PlanetController {
    @Autowired
    private PlanetService planetService;

    @PostMapping
    public ResponseEntity<Planet> create(@RequestBody Planet planet) {
        Planet planetCreated = planetService.create(planet);
        return ResponseEntity.status(HttpStatus.CREATED).body(planetCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> get(@PathVariable("id") Long id) {
        return planetService.get(id).map(planet -> ResponseEntity.ok(planet))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
