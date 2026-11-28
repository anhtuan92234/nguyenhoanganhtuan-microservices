USE room_db;

INSERT INTO buildings (name, address, description, status) VALUES
('Khu trọ Hoa Sen', '123 Lê Văn Việt, TP. Thủ Đức, TP.HCM', 'Khu trọ an ninh, có hầm giữ xe', 'ACTIVE'),
('Chung cư mini Bình An', '45 Nguyễn Văn Tăng, TP. Thủ Đức, TP.HCM', 'Có thang máy, bảo vệ 24/7', 'ACTIVE'),
('Nhà trọ Thanh Xuân', '78 Man Thiện, TP. Thủ Đức, TP.HCM', 'Gần khu công nghệ cao, yên tĩnh', 'ACTIVE'),
('Khu căn hộ SunView', '12 Đỗ Xuân Hợp, TP. Thủ Đức, TP.HCM', 'Phòng mới xây, đầy đủ tiện nghi', 'ACTIVE'),
('Nhà trọ An Lạc', '90 Kha Vạn Cân, TP. Thủ Đức, TP.HCM', 'Giá rẻ cho sinh viên và nhân viên văn phòng', 'ACTIVE');

INSERT INTO room_types (name, amenities, description) VALUES
('Phòng tiêu chuẩn', 'Quạt trần, WC riêng, cửa sổ', 'Phòng ở 1 - 2 người'),
('Phòng có gác lửng', 'Máy lạnh, gác gỗ, WC riêng, kệ bếp', 'Tối ưu diện tích cho 2 - 3 người'),
('Studio full nội thất', 'Máy lạnh, tủ lạnh, máy giặt, giường, tủ đồ', 'Dạng căn hộ nhỏ gọn, dọn vào ở ngay'),
('Phòng 2 mặt thoáng', 'Máy lạnh, ban công, WC riêng, wifi tốc độ cao', 'Ánh sáng tự nhiên tốt, mát mẻ'),
('Phòng gia đình', '2 máy lạnh, tủ lạnh, máy giặt, bếp riêng, WC riêng', 'Phù hợp gia đình nhỏ hoặc nhóm 3 - 4 người');

INSERT INTO rooms (room_number, monthly_price, deposit_amount, area, floor_number, max_occupants, status, description, building_id, room_type_id) VALUES
('P101', 3500000.00, 3500000.00, 24.0, 1, 2, 'AVAILABLE', 'Phòng tầng 1, thuận tiện dắt xe', 1, 1),
('P102', 4200000.00, 4200000.00, 30.0, 1, 3, 'AVAILABLE', 'Có gác lửng rộng, máy lạnh inverter', 1, 2),
('P201', 5500000.00, 5500000.00, 35.0, 2, 2, 'RENTED', 'Studio đầy đủ nội thất', 2, 3),
('P202', 4500000.00, 4500000.00, 28.0, 2, 2, 'AVAILABLE', 'Ban công thoáng mát', 2, 4),
('P301', 3200000.00, 3200000.00, 22.0, 3, 2, 'AVAILABLE', 'Phòng sạch sẽ, giá tốt', 3, 1),
('P302', 6200000.00, 6200000.00, 42.0, 3, 4, 'AVAILABLE', 'Phòng lớn cho gia đình', 4, 5);