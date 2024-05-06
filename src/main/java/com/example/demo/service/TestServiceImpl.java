package com.example.demo.service;


import com.example.demo.mapper.TestMapper;
import com.example.demo.model.TestEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService  {
	private static final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
	
	final TestMapper testMapper;

	@Override
	public TestEntity getOneTest(long id) {
		return testMapper.getOneTest(id);
	}
}
