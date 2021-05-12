package com.example.demo.repository;

import com.example.demo.model.studentRedis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<studentRedis, String> {}
