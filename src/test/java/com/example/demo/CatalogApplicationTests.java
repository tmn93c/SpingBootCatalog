package com.example.demo;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CatalogApplicationTests {

	@Test
	void contextLoads() {
		List mockList = Mockito.mock(List.class);
		Mockito.when(mockList.size()).thenReturn(2);
		Assert.assertEquals(2, mockList.size());
	}
	
	
	
}
