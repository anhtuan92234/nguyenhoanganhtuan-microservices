package com.example.room_service.service;

import com.example.room_service.entity.Room;
import com.example.room_service.entity.RoomImage;
import com.example.room_service.repository.RoomImageRepository;
import com.example.room_service.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomImageService {

    private final RoomImageRepository imageRepository;
    private final RoomRepository roomRepository;

    public RoomImageService(RoomImageRepository imageRepository, RoomRepository roomRepository) {
        this.imageRepository = imageRepository;
        this.roomRepository = roomRepository;
    }

    public List<RoomImage> getAll() {
        return imageRepository.findAll();
    }

    public RoomImage getById(Long id) {
        return imageRepository.findById(id) .orElseThrow(() -> new RuntimeException("Không tìm thấy hình ảnh phòng với ID: " + id));
    }

    public RoomImage create(RoomImage image) {
        if (image.getRoom() == null || image.getRoom().getId() == null) {
            throw new IllegalArgumentException("room.id không được để trống");
        }
        Room room = roomRepository.findById(image.getRoom().getId()) .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng với ID: " + image.getRoom().getId()));
        image.setRoom(room);
        if (image.getIsThumbnail() == null) {
            image.setIsThumbnail(false);
        }
        return imageRepository.save(image);
    }

    public RoomImage update(Long id, RoomImage data) {
        RoomImage existing = getById(id);
        existing.setImageUrl(data.getImageUrl());
        if (data.getIsThumbnail() != null) {
            existing.setIsThumbnail(data.getIsThumbnail());
        }
        if (data.getRoom() != null && data.getRoom().getId() != null) {
            Room room = roomRepository.findById(data.getRoom().getId()) .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng với ID: " + data.getRoom().getId()));
            existing.setRoom(room);
        }
        return imageRepository.save(existing);
    }

    public void delete(Long id) {
        RoomImage existing = getById(id);
        imageRepository.delete(existing);
    }
}