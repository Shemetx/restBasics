package com.epam.esm.dao;

import com.epam.esm.domain.GiftCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * The interface Gift certificate dao.
 */
public interface GiftCertificateDao extends
        JpaSpecificationExecutor<GiftCertificate>,
        JpaRepository<GiftCertificate, Integer>,
        GiftCertificateCustomDao {

}
