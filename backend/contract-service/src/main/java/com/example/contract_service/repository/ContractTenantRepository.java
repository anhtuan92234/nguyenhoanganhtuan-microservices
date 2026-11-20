package com.example.contract_service.repository;

import com.example.contract_service.entity.ContractTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContractTenantRepository extends JpaRepository<ContractTenant, Long> {
    List<ContractTenant> findByContractId(Long contractId);
}