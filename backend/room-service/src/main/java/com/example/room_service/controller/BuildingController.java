package com.example.room_service.controller;

import com.example.room_service.entity.Building;
import com.example.room_service.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingController {

    private final BuildingService service;

    public BuildingController(BuildingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Building> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Building getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<Building> create(@Valid @RequestBody Building building) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(building));
    }

    @PutMapping("/{id}")
    public Building update(@PathVariable Long id, @Valid @RequestBody Building building) {
        return service.update(id, building);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}