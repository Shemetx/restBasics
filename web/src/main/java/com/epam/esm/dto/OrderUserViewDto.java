package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class OrderUserViewDto extends RepresentationModel<OrderUserViewDto> {

    private BigDecimal cost;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime purchaseTime;
    private Set<GiftCertificateDto> certificates;

    public OrderUserViewDto() {
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Set<GiftCertificateDto> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<GiftCertificateDto> certificates) {
        this.certificates = certificates;
    }
}
