package com.example.room_service.service;

import com.example.room_service.dto.RoomRequest;
import com.example.room_service.dto.RoomResponse;
import com.example.room_service.entity.Building;
import com.example.room_service.entity.Room;
import com.example.room_service.entity.RoomType;
import com.example.room_service.exception.ResourceNotFoundException;
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

    public RoomService(RoomRepository roomRepository,BuildingRepository buildingRepository,RoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.buildingRepository = buildingRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    private RoomResponse toResponse(Room room) {
        return new RoomResponse(
                room.getId(),
                room.getRoomNumber(),
                room.getMonthlyPrice(),
                room.getDepositAmount(),
                room.getArea(),
                room.getFloorNumber(),
                room.getMaxOccupants(),
                room.getStatus(),
                room.getDescription(),
                room.getBuilding() != null ? room.getBuilding().getId() : null,
                room.getBuilding() != null ? room.getBuilding().getName() : null,
                room.getRoomType() != null ? room.getRoomType().getId() : null,
                room.getRoomType() != null ? room.getRoomType().getName() : null
        );
    }

    public List<RoomResponse> getAll() {
        return roomRepository.findAll().stream().map(this::toResponse).toList();
    }

    public RoomResponse getById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng với ID: " + id));
        return toResponse(room);
    }

    public List<RoomResponse> getByStatus(String status) {
        return roomRepository.findByStatus(status)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public RoomResponse create(RoomRequest request) {
        Building building = buildingRepository.findById(request.buildingId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khu trọ/tòa nhà với ID: " + request.buildingId()));
        RoomType roomType = roomTypeRepository.findById(request.roomTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy loại phòng với ID: " + request.roomTypeId()));

        Room room = new Room();
        room.setRoomNumber(request.roomNumber());
        room.setMonthlyPrice(request.monthlyPrice());
        room.setDepositAmount(request.depositAmount());
        room.setArea(request.area());
        room.setFloorNumber(request.floorNumber());
        room.setMaxOccupants(request.maxOccupants() != null ? request.maxOccupants() : 2);
        room.setStatus(request.status() != null && !request.status().isBlank() ? request.status() : "AVAILABLE");
        room.setDescription(request.description());
        room.setBuilding(building);
        room.setRoomType(roomType);

        return toResponse(roomRepository.save(room));
    }

    public RoomResponse update(Long id, RoomRequest request) {
        Room existing = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng với ID: " + id));
        Building building = buildingRepository.findById(request.buildingId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khu trọ/tòa nhà với ID: " + request.buildingId()));
        RoomType roomType = roomTypeRepository.findById(request.roomTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy loại phòng với ID: " + request.roomTypeId()));

        existing.setRoomNumber(request.roomNumber());
        existing.setMonthlyPrice(request.monthlyPrice());
        existing.setDepositAmount(request.depositAmount());
        existing.setArea(request.area());
        existing.setFloorNumber(request.floorNumber());
        existing.setMaxOccupants(request.maxOccupants());
        existing.setDescription(request.description());
        existing.setBuilding(building);
        existing.setRoomType(roomType);
        if (request.status() != null && !request.status().isBlank()) {
            existing.setStatus(request.status());
        }

        return toResponse(roomRepository.save(existing));
    }

    public void delete(Long id) {
        Room existing = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng với ID: " + id));
        roomRepository.delete(existing);
    }
}