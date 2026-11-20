package com.example.contract_service.service;

import com.example.contract_service.entity.ContractTenant;
import com.example.contract_service.entity.RentalContract;
import com.example.contract_service.repository.ContractTenantRepository;
import com.example.contract_service.repository.RentalContractRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ContractTenantService {

    private final ContractTenantRepository tenantRepository;
    private final RentalContractRepository contractRepository;

    public ContractTenantService(ContractTenantRepository tenantRepository,
                                 RentalContractRepository contractRepository) {
        this.tenantRepository = tenantRepository;
        this.contractRepository = contractRepository;
    }

    public List<ContractTenant> getAll() {
        return tenantRepository.findAll();
    }

    public ContractTenant getById(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người thuê trong hợp đồng với ID: " + id));
    }

    public ContractTenant create(ContractTenant item) {
        RentalContract contract = validateContract(item.getContract());
        item.setContract(contract);
        if (item.getIsPrimaryHolder() == null) {
            item.setIsPrimaryHolder(false);
        }
        if (item.getJoinedDate() == null) {
            item.setJoinedDate(LocalDate.now());
        }
        return tenantRepository.save(item);
    }

    public ContractTenant update(Long id, ContractTenant data) {
        ContractTenant existing = getById(id);
        if (data.getContract() != null && data.getContract().getId() != null) {
            existing.setContract(validateContract(data.getContract()));
        }
        existing.setTenantId(data.getTenantId());
        existing.setFullName(data.getFullName());
        existing.setIdCardNumber(data.getIdCardNumber());
        if (data.getIsPrimaryHolder() != null) {
            existing.setIsPrimaryHolder(data.getIsPrimaryHolder());
        }
        if (data.getJoinedDate() != null) {
            existing.setJoinedDate(data.getJoinedDate());
        }
        return tenantRepository.save(existing);
    }

    public void delete(Long id) {
        ContractTenant existing = getById(id);
        tenantRepository.delete(existing);
    }

    private RentalContract validateContract(RentalContract contract) {
        if (contract == null || contract.getId() == null) {
            throw new IllegalArgumentException("contract.id không được để trống");
        }
        return contractRepository.findById(contract.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hợp đồng với ID: " + contract.getId()));
    }
}