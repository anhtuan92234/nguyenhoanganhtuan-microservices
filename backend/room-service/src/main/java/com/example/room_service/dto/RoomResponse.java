package com.example.room_service.dto;

import java.math.BigDecimal;

public record RoomResponse(
    Long id,
    String roomNumber,
    BigDecimal monthlyPrice,
    BigDecimal depositAmount,
    Double area,
    Integer floorNumber,
    Integer maxOccupants,
    String status,
    String description,
    Long buildingId,
    String buildingName,
    Long roomTypeId,
    String roomTypeName
) {}