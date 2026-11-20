package com.example.room_service.service;

import com.example.room_service.entity.Building;
import com.example.room_service.repository.BuildingRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BuildingService {

    private final BuildingRepository repository;

    public BuildingService(BuildingRepository repository) {
        this.repository = repository;
    }

    public List<Building> getAll() {
        return repository.findAll();
    }

    public Building getById(Long id) {
        return repository.findById(id) .orElseThrow(() -> new RuntimeException("Không tìm thấy khu trọ/tòa nhà với ID: " + id));
    }

    public Building create(Building building) {
        if (building.getStatus() == null || building.getStatus().isBlank()) {
            building.setStatus("ACTIVE");
        }
        return repository.save(building);
    }

    public Building update(Long id, Building data) {
        Building existing = getById(id);
        existing.setName(data.getName());
        existing.setAddress(data.getAddress());
        existing.setDescription(data.getDescription());
        if (data.getStatus() != null && !data.getStatus().isBlank()) {
            existing.setStatus(data.getStatus());
        }
        return repository.save(existing);
    }

    public void delete(Long id) {
        Building existing = getById(id);
        repository.delete(existing);
    }
}