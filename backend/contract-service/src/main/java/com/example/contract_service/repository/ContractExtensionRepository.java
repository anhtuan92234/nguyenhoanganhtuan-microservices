package com.example.contract_service.repository;

import com.example.contract_service.entity.ContractExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContractExtensionRepository extends JpaRepository<ContractExtension, Long> {
    List<ContractExtension> findByContractId(Long contractId);
}