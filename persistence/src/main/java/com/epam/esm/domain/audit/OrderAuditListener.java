package com.epam.esm.domain.audit;

import com.epam.esm.domain.Order;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

/**
 * The type Order audit listener.
 */
public class OrderAuditListener {

    /**
     * Calls before save order entity
     *
     * @param object order entity
     */
    @PrePersist
    private void beforeCreate(Object object) {
        Order order = (Order) object;
        order.setPurchaseTime(LocalDateTime.now());
    }
}
