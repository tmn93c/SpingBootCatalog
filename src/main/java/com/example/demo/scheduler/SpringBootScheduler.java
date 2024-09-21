package com.example.demo.scheduler;

import com.example.demo.mapper.TestMapper;
import com.example.demo.model.TestEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class SpringBootScheduler {
    Logger logger = LoggerFactory.getLogger(getClass());
    private final TestMapper testMapper;
//    @Scheduled(cron = "0 * * * * *")
    public void checkVar() throws InterruptedException {
        LocalDateTime lt = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        TestEntity userModel = testMapper.getOneTest(1);

        ZonedDateTime gmtDateTime = lt.atZone(ZoneOffset.UTC);
        logger.info("Check Var {} !!!", lt.format(df));
        logger.info("Check Var UTC {} !!!", gmtDateTime.format(df));
        Thread.sleep(10000);
    }
}
