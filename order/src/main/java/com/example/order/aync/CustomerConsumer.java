package com.example.order.aync;


import com.example.common.domain.OrderDetail;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerConsumer {

    @KafkaListener(topics = "${customer.topic.name}", containerFactory = "customerKafkaListenerContainerFactory")
    public void customerOrderDetailListener(OrderDetail orderDetail) {
        System.out.println("<< consume: " + orderDetail);
    }

}
