package com.example.demo.controllers;

import com.example.demo.request.CustomerDto;
import com.example.demo.service.EnqueueDequeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rabbitmq")
public class RabbitMQTestController {

    private final EnqueueDequeService enqueueDequeService;

    public RabbitMQTestController(EnqueueDequeService enqueueDequeService) {
        this.enqueueDequeService = enqueueDequeService;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomerDto customerDto) {
        enqueueDequeService.publishMessage(customerDto);
        return "message saved";
    }
}