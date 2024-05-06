package com.example.demo.repository;

import com.example.demo.model.TradeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeCrudRepository extends CrudRepository<TradeModel, Long> {


//     Page<TradeModel> findByCreatedBy(Long userId, Pageable pageable);

//     long countByCreatedBy(Long userId);

//     List<TradeModel> findByIdIn(List<Long> pollIds);

     List<TradeModel> findByIdIn(List<Long> pollIds, Sort sort);
    
}
