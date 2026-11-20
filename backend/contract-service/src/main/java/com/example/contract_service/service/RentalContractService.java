package com.example.contract_service.service;

import com.example.contract_service.entity.RentalContract;
import com.example.contract_service.repository.RentalContractRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RentalContractService {

    private final RentalContractRepository repository;

    public RentalContractService(RentalContractRepository repository) {
        this.repository = repository;
    }

    public List<RentalContract> getAll() {
        return repository.findAll();
    }

    public RentalContract getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hợp đồng với ID: " + id));
    }

    public List<RentalContract> getByStatus(String status) {
        return repository.findByStatus(status);
    }

    public RentalContract create(RentalContract contract) {
        validateDates(contract);
        if (contract.getStatus() == null || contract.getStatus().isBlank()) {
            contract.setStatus("ACTIVE");
        }
        return repository.save(contract);
    }

    public RentalContract update(Long id, RentalContract data) {
        validateDates(data);
        RentalContract existing = getById(id);
        existing.setContractCode(data.getContractCode());
        existing.setRoomId(data.getRoomId());
        existing.setPrimaryTenantId(data.getPrimaryTenantId());
        existing.setStartDate(data.getStartDate());
        existing.setEndDate(data.getEndDate());
        existing.setMonthlyRent(data.getMonthlyRent());
        existing.setDepositAmount(data.getDepositAmount());
        existing.setNotes(data.getNotes());
        if (data.getStatus() != null && !data.getStatus().isBlank()) {
            existing.setStatus(data.getStatus());
        }
        return repository.save(existing);
    }

    public void delete(Long id) {
        RentalContract existing = getById(id);
        repository.delete(existing);
    }

    private void validateDates(RentalContract contract) {
        if (contract.getStartDate() != null && contract.getEndDate() != null
                && contract.getEndDate().isBefore(contract.getStartDate())) {
            throw new IllegalArgumentException("Ngày kết thúc hợp đồng không được nhỏ hơn ngày bắt đầu");
        }
    }
}