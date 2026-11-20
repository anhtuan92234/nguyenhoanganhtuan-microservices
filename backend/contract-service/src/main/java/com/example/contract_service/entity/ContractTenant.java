package com.example.contract_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "contract_tenants")
public class ContractTenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id", nullable = false)
    private RentalContract contract;

    @NotNull(message = "ID người thuê không được để trống")
    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @NotBlank(message = "Họ tên người ở không được để trống")
    @Size(max = 120)
    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    @Size(max = 20)
    @Column(name = "id_card_number", length = 20)
    private String idCardNumber;

    @Column(name = "is_primary_holder", nullable = false)
    private Boolean isPrimaryHolder = false;

    @Column(name = "joined_date")
    private LocalDate joinedDate;

    public ContractTenant() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public RentalContract getContract() { return contract; }
    public void setContract(RentalContract contract) { this.contract = contract; }

    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getIdCardNumber() { return idCardNumber; }
    public void setIdCardNumber(String idCardNumber) { this.idCardNumber = idCardNumber; }

    public Boolean getIsPrimaryHolder() { return isPrimaryHolder; }
    public void setIsPrimaryHolder(Boolean isPrimaryHolder) { this.isPrimaryHolder = isPrimaryHolder; }

    public LocalDate getJoinedDate() { return joinedDate; }
    public void setJoinedDate(LocalDate joinedDate) { this.joinedDate = joinedDate; }
}