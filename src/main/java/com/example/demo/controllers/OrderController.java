package com.example.demo.controllers;

import com.example.demo.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @GetMapping("/searchAll/{customerId}")
    public ResponseEntity<?> searchAll(@PathVariable("customerId") String customerId) {
        orderService.searchAll(customerId);
        return ResponseEntity.ok(1);
    }
}
