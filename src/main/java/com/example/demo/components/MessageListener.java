package com.example.demo.components;

import com.example.demo.anotation.SkipClass;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@SkipClass
public class MessageListener {

    @Value("${rabbitmq.queue}")
    String  queueName;

    @Value("${rabbitmq.exchange}")
    String exchange;



    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleMessage(String message) {
        System.out.println("Received message from queue " + queueName + ": " + message);
    }
}