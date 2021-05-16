package com.example.demo.RabbitReceiver;

import com.example.demo.RabbitProducer.HelloBinding;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(EnableBinding.class)
public class HelloReceiver {
    @StreamListener(target = HelloBinding.EMP_CHANNEL)
    public void processEmpNameChannel(String msg) {
        System.out.println(msg);
    }
}