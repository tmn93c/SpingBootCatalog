package com.example.demo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class SpringBootScheduler {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 * * * * *")
    public void checkVar() throws InterruptedException {
        LocalDateTime lt = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        ZonedDateTime gmtDateTime = lt.atZone(ZoneOffset.UTC);
        logger.info("Check Var {} !!!", lt.format(df));
        logger.info("Check Var UTC {} !!!", gmtDateTime.format(df));
        Thread.sleep(10000);
    }
}
