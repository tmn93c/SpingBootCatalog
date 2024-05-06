package com.example.demo.repository;

import com.example.demo.model.TradeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeRepository extends JpaRepository<TradeModel, Long> {

    Optional<TradeModel> findById(Long TradeId);

//     Page<TradeModel> findByCreatedBy(Long userId, Pageable pageable);

//     long countByCreatedBy(Long userId);
    
    List<TradeModel> findByIdIn(List<Long> pollIds);

//     List<TradeModel> findByIdIn(List<Long> pollIds, Sort sort);
    List<TradeModel> findAll();
}
