package com.example.order.service;

import com.example.common.domain.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderProducerService orderProducerService;

    public void createOrder(OrderDetail orderDetail) {
        orderProducerService.publishOrder(orderDetail);
    }

}
