package com.example.demo.RabbitProducer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public  interface HelloBinding {
    String EMP_CHANNEL = "greetingChannel";
    @Output("greetingChannel")
    MessageChannel greeting();
}