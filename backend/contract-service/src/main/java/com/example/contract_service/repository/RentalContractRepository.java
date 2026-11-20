package com.example.contract_service.repository;

import com.example.contract_service.entity.RentalContract;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RentalContractRepository extends JpaRepository<RentalContract, Long> {
    List<RentalContract> findByStatus(String status);
    List<RentalContract> findByRoomId(Long roomId);
}