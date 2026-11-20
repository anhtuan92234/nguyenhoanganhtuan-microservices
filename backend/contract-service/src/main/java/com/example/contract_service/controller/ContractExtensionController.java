package com.example.contract_service.controller;

import com.example.contract_service.entity.ContractExtension;
import com.example.contract_service.service.ContractExtensionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contract-extensions")
public class ContractExtensionController {

    private final ContractExtensionService service;

    public ContractExtensionController(ContractExtensionService service) {
        this.service = service;
    }

    @GetMapping
    public List<ContractExtension> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ContractExtension getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<ContractExtension> create(@Valid @RequestBody ContractExtension ext) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(ext));
    }

    @PutMapping("/{id}")
    public ContractExtension update(@PathVariable Long id, @Valid @RequestBody ContractExtension ext) {
        return service.update(id, ext);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}