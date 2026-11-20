package com.example.room_service.service;

import com.example.room_service.entity.Building;
import com.example.room_service.entity.Room;
import com.example.room_service.entity.RoomType;
import com.example.room_service.repository.BuildingRepository;
import com.example.room_service.repository.RoomRepository;
import com.example.room_service.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final BuildingRepository buildingRepository;
    private final RoomTypeRepository roomTypeRepository;

    public RoomService(RoomRepository roomRepository, BuildingRepository buildingRepository, RoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.buildingRepository = buildingRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Room getById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng với ID: " + id));
    }

    public List<Room> getByStatus(String status) {
        return roomRepository.findByStatus(status);
    }

    public Room create(Room room) {
        Building building = validateBuilding(room.getBuilding());
        RoomType roomType = validateRoomType(room.getRoomType());
        room.setBuilding(building);
        room.setRoomType(roomType);
        if (room.getStatus() == null || room.getStatus().isBlank()) {
            room.setStatus("AVAILABLE");
        }
        return roomRepository.save(room);
    }

    public Room update(Long id, Room data) {
        Room existing = getById(id);
        Building building = validateBuilding(data.getBuilding());
        RoomType roomType = validateRoomType(data.getRoomType());

        existing.setRoomNumber(data.getRoomNumber());
        existing.setMonthlyPrice(data.getMonthlyPrice());
        existing.setDepositAmount(data.getDepositAmount());
        existing.setArea(data.getArea());
        existing.setFloorNumber(data.getFloorNumber());
        existing.setMaxOccupants(data.getMaxOccupants());
        existing.setDescription(data.getDescription());
        existing.setBuilding(building);
        existing.setRoomType(roomType);
        if (data.getStatus() != null && !data.getStatus().isBlank()) {
            existing.setStatus(data.getStatus());
        }
        return roomRepository.save(existing);
    }

    public void delete(Long id) {
        Room existing = getById(id);
        roomRepository.delete(existing);
    }

    private Building validateBuilding(Building building) {
        if (building == null || building.getId() == null) {
            throw new IllegalArgumentException("building.id không được để trống");
        }
        return buildingRepository.findById(building.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khu trọ/tòa nhà với ID: " + building.getId()));
    }

    private RoomType validateRoomType(RoomType roomType) {
        if (roomType == null || roomType.getId() == null) {
            throw new IllegalArgumentException("roomType.id không được để trống");
        }
        return roomTypeRepository.findById(roomType.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại phòng với ID: " + roomType.getId()));
    }
}