package com.example.demo.repository;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.google.common.base.Predicate;

import io.micrometer.core.lang.Nullable;

public interface Specification<T> extends Serializable {

    @Nullable
    Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, 
                CriteriaBuilder criteriaBuilder);
    
    // ... other methods
}