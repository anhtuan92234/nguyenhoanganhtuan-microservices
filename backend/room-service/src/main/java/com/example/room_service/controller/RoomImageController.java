package com.example.room_service.controller;

import com.example.room_service.entity.RoomImage;
import com.example.room_service.service.RoomImageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/room-images")
public class RoomImageController {

    private final RoomImageService service;

    public RoomImageController(RoomImageService service) {
        this.service = service;
    }

    @GetMapping
    public List<RoomImage> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RoomImage getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<RoomImage> create(@Valid @RequestBody RoomImage roomImage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(roomImage));
    }

    @PutMapping("/{id}")
    public RoomImage update(@PathVariable Long id, @Valid @RequestBody RoomImage roomImage) {
        return service.update(id, roomImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}