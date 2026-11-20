package com.example.room_service.service;

import com.example.room_service.entity.RoomType;
import com.example.room_service.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomTypeService {

    private final RoomTypeRepository repository;

    public RoomTypeService(RoomTypeRepository repository) {
        this.repository = repository;
    }

    public List<RoomType> getAll() {
        return repository.findAll();
    }

    public RoomType getById(Long id) {
        return repository.findById(id) .orElseThrow(() -> new RuntimeException("Không tìm thấy loại phòng với ID: " + id));
    }

    public RoomType create(RoomType roomType) {
        return repository.save(roomType);
    }

    public RoomType update(Long id, RoomType data) {
        RoomType existing = getById(id);
        existing.setName(data.getName());
        existing.setAmenities(data.getAmenities());
        existing.setDescription(data.getDescription());
        return repository.save(existing);
    }

    public void delete(Long id) {
        RoomType existing = getById(id);
        repository.delete(existing);
    }
}