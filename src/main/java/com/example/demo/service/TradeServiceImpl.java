package com.example.demo.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TradeModel;
import com.example.demo.repository.TradeCrudRepository;
import com.example.demo.repository.TradeRepository;
import com.example.demo.request.EnterpriseGetRowsRequest;
import com.example.demo.request.TradeRequest;
import com.example.demo.response.PagedResponse;
import com.example.demo.response.TradeIdResponse;
import com.example.demo.response.TradeResponse;
import com.example.demo.scheduler.MyQuatzScheduler;
import com.example.demo.util.AppConstants;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MyQuatzScheduler myQuatzScheduler;

    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private TradeCrudRepository tradeCrudRepository;

    private EntityManager em;
    
    public void createJob(String id){
        myQuatzScheduler.perDay(id);
    }
    public TradeModel createTrace(TradeRequest request) {
        var trademodel = new TradeModel();
         trademodel = modelMapper.map(request,TradeModel.class);
        return tradeRepository.save(trademodel);
    }
    public TradeIdResponse updateTrace(Long tradeId,TradeRequest request) {
        TradeModel tradefid = tradeRepository.findById(tradeId)
                .orElseThrow(() -> new ResourceNotFoundException("Trade", "id", tradeId));
        tradefid =  modelMapper.map(request,TradeModel.class);
        tradefid.setId(tradeId);
        var traderes = new TradeIdResponse();
        tradeRepository.save(tradefid);
        traderes = modelMapper.map(tradefid,TradeIdResponse.class);
        return traderes;
    }
    public TradeIdResponse getTradeById(Long TradeId) {
        TradeModel tradefid = tradeRepository.findById(TradeId).orElseThrow(
                () -> new ResourceNotFoundException("Trade", "id", TradeId));
        var traderes = new TradeIdResponse();
        traderes = modelMapper.map(tradefid,TradeIdResponse.class);
        return traderes;
    }
    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    public PagedResponse<TradeResponse> getAllTrades(EnterpriseGetRowsRequest request) {
        validatePageNumberAndSize(1, 10);
        Pageable pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "id");
        Page<TradeModel> Trades = tradeRepository.findAll(pageable);

        if(Trades.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), Trades.getNumber(),
            Trades.getSize(), Trades.getTotalElements(), Trades.getTotalPages(), Trades.isLast());
        }
    
        // Map Polls to PollResponses containing vote counts and poll creator details
        List<Long> tradeIds = Trades.map(TradeModel::getId).getContent();

        // modelMapper.map(tradeIds,TradeResponse.class);

        return new PagedResponse<TradeResponse>();
    }

	public List<TradeModel> getTrades() 
	{
		return (List<TradeModel>) tradeCrudRepository.findAll();
	}

    public List<TradeModel> getTradesByListIds(List<Long> Ids) 
	{
		return (List<TradeModel>) tradeRepository.findAllById(Ids);
	}
    
}