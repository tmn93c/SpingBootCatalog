package com.example.demo.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class quatzWatchJob extends QuartzJobBean {

        private static final Logger logger = LoggerFactory.getLogger(quatzWatchJob.class);
        private final Scheduler scheduler;
        public quatzWatchJob(
        Scheduler scheduler
        ) {
            this.scheduler = scheduler;
        }
        @Override
        protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            JobKey key = context.getJobDetail().getKey();
            logger.info("Watch Job !!! {} - {}",key,now);
            try {
                logger.info("Group {} number of jobs : {}",scheduler.getJobGroupNames(),scheduler.getJobKeys(GroupMatcher.jobGroupEquals("GROUP_QUATZ")).size());
            } catch (SchedulerException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                if (context.getNextFireTime() == null && context.getScheduler().getCurrentlyExecutingJobs().size() == 1) {
                    JobKey sj = context.getJobDetail().getKey();
                    scheduler.deleteJob(sj);
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
}
