package com.epam.esm.service.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.impl.OrderDaoImpl;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Order;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.OrderService;
import com.epam.esm.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of OrderService
 */
@Component
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private GiftCertificateService certificateService;
    private PageUtil pageUtil;
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Sets page util.
     *
     * @param pageUtil the page util
     */
    @Autowired
    public void setPageUtil(PageUtil pageUtil) {
        this.pageUtil = pageUtil;
    }

    /**
     * Sets certificate service.
     *
     * @param certificateService the certificate service
     */
    @Autowired
    public void setCertificateService(GiftCertificateServiceImpl certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * Sets order dao.
     *
     * @param orderDao the order dao
     */
    @Autowired
    public void setOrderDao(OrderDaoImpl orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    @Override
    public Order save(Order order) {
        userService.findById(order.getCustomer().getId());
        Set<GiftCertificate> certificates = order.getCertificates();
        Set<GiftCertificate> certificateSet = certificateIdToEntity(certificates);
        double collect = certificateSet.stream().mapToDouble(GiftCertificate::getPrice).sum();
        order.setCertificates(certificateSet);
        order.setCost(BigDecimal.valueOf(collect).setScale(3, BigDecimal.ROUND_DOWN));
        return orderDao.save(order);
    }

    /**
     * Transform id's into entities
     *
     * @param certificates Set оf certificates filled with id's
     * @return set of certificates
     */
    private Set<GiftCertificate> certificateIdToEntity(Set<GiftCertificate> certificates) {
        return certificates.stream().map(temp -> certificateService.findById(temp.getId()))
                .collect(Collectors.toSet());
    }

    @Override
    public List<Order> findAll(int page, int size) {
        page = pageUtil.getCorrectPage(page, size);
        return orderDao.findAll(page, size);
    }

    @Override
    public List<Order> findByUserId(int id, int page, int size) {
        page = pageUtil.getCorrectPage(page, size);
        List<Order> byUserId = orderDao.findByUserId(id, page, size);
        if (byUserId.isEmpty()) {
            throw new EntityNotFoundException("User with id: '" + id + "' not found");
        }
        return byUserId;
    }

    @Override
    public Order findById(int id) {
        Order byId = orderDao.findById(id);
        if (byId == null) {
            throw new EntityNotFoundException("Order with id: '" + id + "' not found");
        }
        return byId;
    }

}
