package com.example.room_service.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record RoomRequest(
    @NotBlank(message = "Mã/số phòng không được để trống")
    @Size(max = 50, message = "Mã/số phòng tối đa 50 ký tự")
    String roomNumber,

    @NotNull(message = "Giá thuê hàng tháng không được để trống")
    @Positive(message = "Giá thuê phải lớn hơn 0")
    BigDecimal monthlyPrice,

    @NotNull(message = "Tiền đặt cọc không được để trống")
    @PositiveOrZero(message = "Tiền đặt cọc phải lớn hơn hoặc bằng 0")
    BigDecimal depositAmount,

    @NotNull(message = "Diện tích không được để trống")
    @Positive(message = "Diện tích phải lớn hơn 0")
    Double area,

    @Min(value = 0, message = "Số tầng không được âm")
    Integer floorNumber,

    @Min(value = 1, message = "Số người ở tối đa phải ít nhất là 1")
    Integer maxOccupants,

    @Pattern(regexp = "AVAILABLE|RENTED|MAINTENANCE", message = "Trạng thái phòng chỉ nhận AVAILABLE, RENTED hoặc MAINTENANCE")
    String status,

    String description,

    @NotNull(message = "ID khu trọ/tòa nhà không được để trống")
    @Positive(message = "ID khu trọ/tòa nhà phải lớn hơn 0")
    Long buildingId,

    @NotNull(message = "ID loại phòng không được để trống")
    @Positive(message = "ID loại phòng phải lớn hơn 0")
    Long roomTypeId
) {}