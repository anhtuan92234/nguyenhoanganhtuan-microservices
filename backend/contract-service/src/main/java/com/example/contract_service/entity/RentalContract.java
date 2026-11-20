package com.example.contract_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contracts")
public class RentalContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mã hợp đồng không được để trống")
    @Size(max = 50)
    @Column(name = "contract_code", nullable = false, unique = true, length = 50)
    private String contractCode;

    @NotNull(message = "ID phòng không được để trống")
    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @NotNull(message = "ID người thuê chính không được để trống")
    @Column(name = "primary_tenant_id", nullable = false)
    private Long primaryTenantId;

    @NotNull(message = "Ngày bắt đầu thuê không được để trống")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "Ngày kết thúc hợp đồng không được để trống")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull(message = "Tiền thuê hàng tháng không được để trống")
    @Positive(message = "Tiền thuê phải lớn hơn 0")
    @Column(name = "monthly_rent", nullable = false, precision = 15, scale = 2)
    private BigDecimal monthlyRent;

    @NotNull(message = "Tiền đặt cọc không được để trống")
    @Column(name = "deposit_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal depositAmount;

    @Size(max = 30)
    @Column(name = "status", nullable = false, length = 30)
    private String status = "ACTIVE"; // ACTIVE, EXPIRED, TERMINATED

    @Size(max = 500)
    @Column(name = "notes", length = 500)
    private String notes;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ContractTenant> occupants = new ArrayList<>();

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ContractExtension> extensions = new ArrayList<>();

    public RentalContract() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContractCode() { return contractCode; }
    public void setContractCode(String contractCode) { this.contractCode = contractCode; }

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public Long getPrimaryTenantId() { return primaryTenantId; }
    public void setPrimaryTenantId(Long primaryTenantId) { this.primaryTenantId = primaryTenantId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public BigDecimal getMonthlyRent() { return monthlyRent; }
    public void setMonthlyRent(BigDecimal monthlyRent) { this.monthlyRent = monthlyRent; }

    public BigDecimal getDepositAmount() { return depositAmount; }
    public void setDepositAmount(BigDecimal depositAmount) { this.depositAmount = depositAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<ContractTenant> getOccupants() { return occupants; }
    public void setOccupants(List<ContractTenant> occupants) { this.occupants = occupants; }

    public List<ContractExtension> getExtensions() { return extensions; }
    public void setExtensions(List<ContractExtension> extensions) { this.extensions = extensions; }
}