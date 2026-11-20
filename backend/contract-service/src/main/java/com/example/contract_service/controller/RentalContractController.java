package com.example.contract_service.controller;

import com.example.contract_service.entity.RentalContract;
import com.example.contract_service.service.RentalContractService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class RentalContractController {

    private final RentalContractService service;

    public RentalContractController(RentalContractService service) {
        this.service = service;
    }

    @GetMapping
    public List<RentalContract> getAll(@RequestParam(required = false) String status) {
        if (status != null && !status.isBlank()) {
            return service.getByStatus(status);
        }
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RentalContract getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<RentalContract> create(@Valid @RequestBody RentalContract contract) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(contract));
    }

    @PutMapping("/{id}")
    public RentalContract update(@PathVariable Long id, @Valid @RequestBody RentalContract contract) {
        return service.update(id, contract);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}