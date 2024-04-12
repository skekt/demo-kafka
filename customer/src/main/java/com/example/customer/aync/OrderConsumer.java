package com.example.customer.aync;

import com.example.common.domain.OrderDetail;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @KafkaListener(topics = "${order.topic.name}", containerFactory = "orderKafkaListenerContainerFactory")
    public void orderListener(OrderDetail orderDetail) {
        System.out.println("<< consume: " + orderDetail);
    }

}
