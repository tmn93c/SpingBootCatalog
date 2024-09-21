package com.example.demo.service;


import com.example.demo.mapper.TestMapper;
import com.example.demo.model.TestEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService  {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	final TestMapper testMapper;

	@Override
	@Synchronized
	@Transactional
	public TestEntity getOneTest(long id, String pcs) {
		log.info("TEST !!! {}", LocalDateTime.now());
		runCal(pcs);
		return testMapper.getOneTest(id);
	}

	private void runCal(String pcs) {
		testMapper.updateProcess(pcs);
		simulateDelay(3);
	}

	public static void simulateDelay(int seconds) {
		long endTime = System.currentTimeMillis() + (seconds * 1000);
		while (System.currentTimeMillis() < endTime) {
			// Busy-waiting, simulating work
			// You can perform some minimal operation here if needed
		}
	}
}
