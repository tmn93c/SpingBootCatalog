package com.example.demo.controllers;

import com.example.demo.request.EnterpriseGetRowsRequest;
import com.example.demo.request.TradeRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.EnterpriseGetRowsResponse;
import com.example.demo.response.PagedResponse;
import com.example.demo.response.TradeIdResponse;
import com.example.demo.response.TradeResponse;
import com.example.demo.service.TradeServiceImpl;
import com.example.demo.dao.TradeDao;
import com.example.demo.model.TradeModel;
import com.example.demo.payload.GetTradeByIdsRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private TradeDao tradeDao;

    @Autowired
    private TradeServiceImpl tradeService;

    @Autowired
    public TradeController(@Qualifier("tradeDao") TradeDao tradeDao) {
        this.tradeDao = tradeDao;
    }

    @RequestMapping({ "/quartz/{id}" })
	public String quartz(@PathVariable String id) {
        tradeService.createJob(id);
		return "Hello World";
	}

    @RequestMapping(method = POST, value = "/getAll")
    @ResponseBody
    public List<TradeModel> getRows() {
        return tradeService.getTrades();
    }

    @RequestMapping(method = POST, value = "/getRows")
    @ResponseBody
    public EnterpriseGetRowsResponse getRows(@RequestBody EnterpriseGetRowsRequest request) {
        return tradeDao.getData(request);
    }
    @RequestMapping(method = POST, value = "/getRowsJPA")
    @ResponseBody
    public PagedResponse<TradeResponse> getRowsJPA(@RequestBody EnterpriseGetRowsRequest request) {
        return tradeService.getAllTrades(request);
    }

    @RequestMapping(method = POST, value = "/getRowsByListIds")
    @ResponseBody
    public List<TradeModel> getTradesByListIds(@RequestBody GetTradeByIdsRequest request) {
        return tradeService.getTrades();
    }
    
    @PostMapping
    public  ResponseEntity<?> createTrade(@Valid @RequestBody TradeRequest request) {
        TradeModel trade = tradeService.createTrace(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{TradeId}")
                .buildAndExpand(trade.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Trade Created Successfully"));
    }
    @GetMapping("/{TradeId}")
    public TradeIdResponse getTradeById(
                                    @PathVariable Long TradeId) {
        return tradeService.getTradeById(TradeId);
    }

    @PostMapping("/{tradeId}")
    public TradeIdResponse updateTradeId(
                         @PathVariable Long tradeId,
                         @Valid @RequestBody TradeRequest tradeRequest) {
        return tradeService.updateTrace(tradeId, tradeRequest);
    }
}