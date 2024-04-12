package com.example.order.controller;

import com.example.common.domain.OrderDetail;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class OrderController {

    private OrderService orderService;


    private AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orders/{count}", method = RequestMethod.POST)
    public List<Long> createOrder(@PathVariable Long count) {
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Long id = Long.valueOf(counter.incrementAndGet());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(id);
            orderDetail.setLabel("Order No: " + id);

            orderService.createOrder(orderDetail);

            ids.add(id);
        }

        return ids;
    }

}
