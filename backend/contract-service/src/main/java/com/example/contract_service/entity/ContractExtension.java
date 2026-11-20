package com.example.contract_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contract_extensions")
public class ContractExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id", nullable = false)
    private RentalContract contract;

    @NotNull(message = "Ngày kết thúc mới không được để trống")
    @Column(name = "new_end_date", nullable = false)
    private LocalDate newEndDate;

    @NotNull(message = "Giá thuê gia hạn không được để trống")
    @Positive(message = "Giá thuê gia hạn phải lớn hơn 0")
    @Column(name = "new_monthly_rent", nullable = false, precision = 15, scale = 2)
    private BigDecimal newMonthlyRent;

    @Column(name = "extended_date", nullable = false)
    private LocalDate extendedDate = LocalDate.now();

    @Size(max = 300)
    @Column(name = "reason", length = 300)
    private String reason;

    public ContractExtension() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public RentalContract getContract() { return contract; }
    public void setContract(RentalContract contract) { this.contract = contract; }

    public LocalDate getNewEndDate() { return newEndDate; }
    public void setNewEndDate(LocalDate newEndDate) { this.newEndDate = newEndDate; }

    public BigDecimal getNewMonthlyRent() { return newMonthlyRent; }
    public void setNewMonthlyRent(BigDecimal newMonthlyRent) { this.newMonthlyRent = newMonthlyRent; }

    public LocalDate getExtendedDate() { return extendedDate; }
    public void setExtendedDate(LocalDate extendedDate) { this.extendedDate = extendedDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}