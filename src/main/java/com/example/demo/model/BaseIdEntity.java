package com.example.demo.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseIdEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

}