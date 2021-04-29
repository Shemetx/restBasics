package com.epam.esm.domain.audit;

import com.epam.esm.domain.GiftCertificate;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * The type Certificate audit listener.
 */
public class CertificateAuditListener {

    /**
     * Calls before save certificate entity
     *
     * @param object certificate entity
     */
    @PrePersist
    private void beforeCreate(Object object) {
        GiftCertificate certificate = (GiftCertificate) object;
        certificate.setLastUpdateDate(LocalDateTime.now());
        certificate.setCreateDate(LocalDateTime.now());
    }

    /**
     * Calls before update certificate entity
     *
     * @param object certificate entity
     */
    @PreUpdate
    private void beforeUpdate(Object object) {
        GiftCertificate certificate = (GiftCertificate) object;
        certificate.setLastUpdateDate(LocalDateTime.now());
    }
}
