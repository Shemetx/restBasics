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
     * Calls before create certificate entity
     *
     * @param certificate entity
     */
    @PrePersist
    private void beforeCreate(GiftCertificate certificate) {
        certificate.setLastUpdateDate(LocalDateTime.now());
        certificate.setCreateDate(LocalDateTime.now());
    }

    /**
     * Calls before update certificate entity
     *
     * @param certificate entity
     */
    @PreUpdate
    private void beforeUpdate(GiftCertificate certificate) {
        certificate.setLastUpdateDate(LocalDateTime.now());
    }
}
