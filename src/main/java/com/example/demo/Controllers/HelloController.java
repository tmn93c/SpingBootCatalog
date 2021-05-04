package com.example.demo.Controllers;

import javax.validation.Valid;

import com.example.demo.request.TradeRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
	@RequestMapping({ "/" })
	public String firstPage() {
		return "Hello World";
	}
    @PostMapping(value="/vals",produces = "application/json")
    public  TradeRequest process(@Valid @RequestBody TradeRequest request) {

		return request;
    }
}
