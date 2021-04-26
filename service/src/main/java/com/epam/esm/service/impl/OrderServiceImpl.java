package com.epam.esm.service.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.impl.OrderDaoImpl;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Order;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.OrderService;
import com.epam.esm.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private GiftCertificateService certificateService;
    private PageUtil pageUtil;

    @Autowired
    public void setPageUtil(PageUtil pageUtil) {
        this.pageUtil = pageUtil;
    }

    @Autowired
    public void setCertificateService(GiftCertificateServiceImpl certificateService) {
        this.certificateService = certificateService;
    }

    @Autowired
    public void setOrderDao(OrderDaoImpl orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    @Override
    public Order save(Order order) {
        Set<GiftCertificate> certificates = order.getCertificates();
        Set<GiftCertificate> certificateSet = certificateIdToEntity(certificates);
        double collect = certificateSet.stream().mapToDouble(GiftCertificate::getPrice).sum();
        order.setCertificates(certificateSet);
        order.setCost((float) collect);
        order.setPurchaseTime(LocalDateTime.now());
        return orderDao.save(order);
    }

    private Set<GiftCertificate> certificateIdToEntity(Set<GiftCertificate> certificates) {
        return certificates.stream().map(temp -> certificateService.findById(temp.getId()))
                .collect(Collectors.toSet());
    }

    @Override
    public List<Order> findAll(int page,int size) {
        page = pageUtil.getCorrectPage(page,size);
        return orderDao.findAll(page,size);
    }

    @Override
    public List<Order> findByUserId(int id,int page,int size) {
        page = pageUtil.getCorrectPage(page,size);
        return orderDao.findByUserId(id,page,size);
    }

}
