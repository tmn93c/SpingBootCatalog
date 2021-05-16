package com.example.demo;

import java.util.List;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserSpeRepository;
import com.example.demo.repository.UserSpecification;
import com.example.demo.util.SearchCriteria;
import com.example.demo.util.AppConstants.SearchOperation;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class CatalogApplicationTests {

	@Test
	void contextLoads() {
		List mockList = Mockito.mock(List.class);
		Mockito.when(mockList.size()).thenReturn(2);
		Assert.assertEquals(2, mockList.size());
	}
	
	
	
}
