package com.example.contract_service.service;

import com.example.contract_service.entity.ContractExtension;
import com.example.contract_service.entity.RentalContract;
import com.example.contract_service.repository.ContractExtensionRepository;
import com.example.contract_service.repository.RentalContractRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ContractExtensionService {

    private final ContractExtensionRepository extensionRepository;
    private final RentalContractRepository contractRepository;

    public ContractExtensionService(ContractExtensionRepository extensionRepository,
                                    RentalContractRepository contractRepository) {
        this.extensionRepository = extensionRepository;
        this.contractRepository = contractRepository;
    }

    public List<ContractExtension> getAll() {
        return extensionRepository.findAll();
    }

    public ContractExtension getById(Long id) {
        return extensionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu gia hạn với ID: " + id));
    }

    public ContractExtension create(ContractExtension ext) {
        if (ext.getContract() == null || ext.getContract().getId() == null) {
            throw new IllegalArgumentException("contract.id không được để trống");
        }
        RentalContract contract = contractRepository.findById(ext.getContract().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hợp đồng với ID: " + ext.getContract().getId()));

        if (ext.getNewEndDate().isBefore(contract.getEndDate())) {
            throw new IllegalArgumentException("Ngày kết thúc gia hạn mới phải lớn hơn hoặc bằng ngày kết thúc hiện tại");
        }

        ext.setContract(contract);
        if (ext.getExtendedDate() == null) {
            ext.setExtendedDate(LocalDate.now());
        }

        ContractExtension saved = extensionRepository.save(ext);

        // Cập nhật ngày kết thúc và giá thuê mới cho hợp đồng
        contract.setEndDate(ext.getNewEndDate());
        contract.setMonthlyRent(ext.getNewMonthlyRent());
        contractRepository.save(contract);

        return saved;
    }

    public ContractExtension update(Long id, ContractExtension data) {
        ContractExtension existing = getById(id);
        existing.setNewEndDate(data.getNewEndDate());
        existing.setNewMonthlyRent(data.getNewMonthlyRent());
        existing.setReason(data.getReason());
        if (data.getExtendedDate() != null) {
            existing.setExtendedDate(data.getExtendedDate());
        }
        return extensionRepository.save(existing);
    }

    public void delete(Long id) {
        ContractExtension existing = getById(id);
        extensionRepository.delete(existing);
    }
}