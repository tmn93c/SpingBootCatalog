package com.example.demo.service;

import java.util.List;

import com.example.demo.model.TradeModel;
import com.example.demo.request.EnterpriseGetRowsRequest;
import com.example.demo.request.TradeRequest;
import com.example.demo.response.PagedResponse;
import com.example.demo.response.TradeIdResponse;
import com.example.demo.response.TradeResponse;

public interface TradeService {
	void createJob(String id);
	TradeModel createTrace(TradeRequest request) ;
	TradeIdResponse updateTrace(Long tradeId,TradeRequest request);
	TradeIdResponse getTradeById(Long TradeId);
	PagedResponse<TradeResponse> getAllTrades(EnterpriseGetRowsRequest request);
	List<TradeModel> getTrades();
	List<TradeModel> getTradesByListIds(List<Long> Ids);

}