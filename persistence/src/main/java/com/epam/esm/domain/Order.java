package com.epam.esm.domain;


import com.epam.esm.domain.audit.OrderAuditListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Order.
 */
@EntityListeners(OrderAuditListener.class)
@Entity
@Table(name = "user_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cost", updatable = false)
    private BigDecimal cost;
    @Column(name = "purchase_time", updatable = false)
    private LocalDateTime purchaseTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User customer;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_items",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    private Set<GiftCertificate> certificates = new HashSet<>();

    /**
     * Instantiates a new Order.
     */
    public Order() {
    }

    /**
     * Instantiates a new Order.
     *
     * @param id           the id
     * @param cost         the cost
     * @param purchaseTime the purchase time
     */
    public Order(Integer id, BigDecimal cost, LocalDateTime purchaseTime) {
        this.id = id;
        this.cost = cost;
        this.purchaseTime = purchaseTime;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
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
    public Set<GiftCertificate> getCertificates() {
        return certificates;
    }

    /**
     * Sets certificates.
     *
     * @param certificates the certificates
     */
    public void setCertificates(Set<GiftCertificate> certificates) {
        this.certificates = certificates;
    }
}
