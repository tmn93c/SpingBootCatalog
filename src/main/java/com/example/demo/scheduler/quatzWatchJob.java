package com.example.demo.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class quatzWatchJob extends QuartzJobBean {

        private static final Logger logger = LoggerFactory.getLogger(quatzWatchJob.class);
        
        @Override
        protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
            logger.info("Watch Job !!!");
        }
}
