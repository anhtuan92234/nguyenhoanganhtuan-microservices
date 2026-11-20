package com.example.room_service.controller;

import com.example.room_service.entity.RoomType;
import com.example.room_service.service.RoomTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/room-types")
public class RoomTypeController {

    private final RoomTypeService service;

    public RoomTypeController(RoomTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<RoomType> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RoomType getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<RoomType> create(@Valid @RequestBody RoomType roomType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(roomType));
    }

    @PutMapping("/{id}")
    public RoomType update(@PathVariable Long id, @Valid @RequestBody RoomType roomType) {
        return service.update(id, roomType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}