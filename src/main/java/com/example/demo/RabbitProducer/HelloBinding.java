package com.example.demo.RabbitProducer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public  interface HelloBinding {

    @Output("greetingChannel")
    MessageChannel greeting();
}