package com.example.order.service;

import com.example.common.domain.OrderDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class OrderProducerService {

    @Autowired
    private KafkaTemplate<String, OrderDetail> orderKafkaTemplate;

    @Value(value = "${order.topic.name}")
    private String orderTopicName;

    public void publishOrder(OrderDetail orderDetail) {
        System.out.println(">> publish: " + orderDetail);

        ListenableFuture<SendResult<String, OrderDetail>> future = orderKafkaTemplate.send(orderTopicName, orderDetail);
        future.addCallback(new ListenableFutureCallback<>() {
            private final Logger LOGGER = LoggerFactory.getLogger(OrderProducerService.class);

            @Override
            public void onSuccess(SendResult<String, OrderDetail> result) {
                OrderDetail _orderDetail = result.getProducerRecord().value();
                LOGGER.info("Sent message=[" + _orderDetail.getId() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable t) {
                // needed to do compensation transaction.
                LOGGER.error("Unable to send message=[" + orderDetail.getId() + "] due to : " + t.getMessage(), t);
            }
        });
    }

}
