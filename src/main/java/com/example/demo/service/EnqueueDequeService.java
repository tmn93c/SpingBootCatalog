package com.example.demo.service;

import com.example.demo.request.CustomerDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnqueueDequeService {

    private final AmqpTemplate amqpTemplate;


    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public EnqueueDequeService(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishMessage(CustomerDto customerDto){
        amqpTemplate.convertAndSend(exchange,routingkey,customerDto);
    }
}