package com.example.contract_service.controller;

import com.example.contract_service.entity.ContractTenant;
import com.example.contract_service.service.ContractTenantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contract-tenants")
public class ContractTenantController {

    private final ContractTenantService service;

    public ContractTenantController(ContractTenantService service) {
        this.service = service;
    }

    @GetMapping
    public List<ContractTenant> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ContractTenant getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<ContractTenant> create(@Valid @RequestBody ContractTenant item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(item));
    }

    @PutMapping("/{id}")
    public ContractTenant update(@PathVariable Long id, @Valid @RequestBody ContractTenant item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}