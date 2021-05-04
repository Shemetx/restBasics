package com.epam.esm.dto;

import com.epam.esm.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * The type Order dto entity.
 */
public class OrderDto extends RepresentationModel<OrderDto> {

    private int id;
    private BigDecimal cost;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime purchaseTime;
    private User customer;
    private Set<GiftCertificateDto> certificates;

    /**
     * Instantiates a new Order dto.
     */
    public OrderDto() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Gets purchase time.
     *
     * @return the purchase time
     */
    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    /**
     * Sets purchase time.
     *
     * @param purchaseTime the purchase time
     */
    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public User getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(User customer) {
        this.customer = customer;
    }

    /**
     * Gets certificates.
     *
     * @return the certificates
     */
    public Set<GiftCertificateDto> getCertificates() {
        return certificates;
    }

    /**
     * Sets certificates.
     *
     * @param certificates the certificates
     */
    public void setCertificates(Set<GiftCertificateDto> certificates) {
        this.certificates = certificates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id)
                && Objects.equals(cost, orderDto.cost)
                && Objects.equals(purchaseTime, orderDto.purchaseTime)
                && Objects.equals(customer, orderDto.customer)
                && Objects.equals(certificates, orderDto.certificates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, purchaseTime, customer, certificates);
    }
}
