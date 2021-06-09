package com.example.demo.service;

import java.util.List;

import com.example.demo.model.TradeModel;
import com.example.demo.request.EnterpriseGetRowsRequest;
import com.example.demo.request.TradeRequest;
import com.example.demo.response.TradeIdResponse;
import com.example.demo.response.TradeResponse;

public interface TradeService {
	public TradeModel createTrace(TradeRequest request) ;
	public TradeIdResponse updateTrace(Long tradeId,TradeRequest request);
	public TradeIdResponse getTradeById(Long TradeId);
	public com.example.demo.response.PagedResponse<TradeResponse> getAllTrades(EnterpriseGetRowsRequest request);
	public List<TradeModel> getTrades();

}