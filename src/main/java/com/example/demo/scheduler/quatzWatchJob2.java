package com.example.demo.scheduler;

import java.time.LocalDateTime;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class quatzWatchJob2 extends QuartzJobBean {

        private static final Logger logger = LoggerFactory.getLogger(quatzWatchJob.class);
        private final Scheduler scheduler;
        public quatzWatchJob2(
        Scheduler scheduler
        ) {
            this.scheduler = scheduler;
        }
        @Override
        protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
            LocalDateTime now = LocalDateTime.now();
            JobKey sj = context.getJobDetail().getKey();
            logger.info("Watch Job 2 !!! {}",now);
            try {
                if (context.getNextFireTime() == null && context.getScheduler().getCurrentlyExecutingJobs().size() == 1) {
                    scheduler.deleteJob(sj);
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
}
