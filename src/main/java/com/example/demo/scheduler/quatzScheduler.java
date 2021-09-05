package com.example.demo.scheduler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import com.example.demo.Config.AutoWiringSpringBeanJobFactory;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@Configuration
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='true'")
public class quatzScheduler {

    Logger logger = LoggerFactory.getLogger(getClass());
    final static String GROUP_KEY = "GROUP_QUATZ";
    final static String GROUP_KEY_TRIGGER = "GROUP_QUATZ_TRIGGER";


    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        logger.info("quatzScheduler from Quartz...");
    }

    @Scheduled(initialDelay = 1000 ,fixedDelay = 5000)
    public void perDay() throws SchedulerException {
        logger.info("A Day from Quartz...");
        runQuartz();
    }
    //https://www.tabnine.com/code/java/methods/org.quartz.Scheduler/deleteJob
    public void runQuartz() throws SchedulerException{
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        for (String groupName : scheduler.getJobGroupNames()) {
    	    logger.info("Group {} number of jobs : {}",scheduler.getJobGroupNames(),scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName)).size());
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                        
            String jobName = jobKey.getName();
            String jobGroup = jobKey.getGroup();
                        
            //get job's trigger
            List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
            Date nextFireTime = triggers.get(0).getNextFireTime(); 

                System.out.println("[jobName] : " + jobName + " [groupName] : "
                    + jobGroup + " - " + nextFireTime);

            }

        }
        // create a job
        JobKey jobKeyA = new JobKey("Qrtz_Job_Detail",GROUP_KEY);
        createJob(scheduler,jobKeyA);
    }

    // @Bean
    // public SpringBeanJobFactory springBeanJobFactory() {
    //     AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
    //     logger.debug("Configuring Job factory");

    //     jobFactory.setApplicationContext(applicationContext);
    //     return jobFactory;
    // }

    // @Bean
    // public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
    //     SchedulerFactoryBean factory = new SchedulerFactoryBean();
    //     factory.setJobFactory(springBeanJobFactory());
    //     factory.setQuartzProperties(quartzProperties());
    //     return factory;
    // }

    // public Properties quartzProperties() throws IOException {
    //     PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    //     propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
    //     propertiesFactoryBean.afterPropertiesSet();
    //     return propertiesFactoryBean.getObject();
    // }

    public void createJob(Scheduler scheduler,JobKey jobKey) {
        // try {
        //     if (!scheduler.deleteJob(jobId)) {
        //       logger.warn("The job belonging to this token could not be deleted.");
        //     }
        //   } catch (SchedulerException e) {
        //     logger.error("An error occurred while cancelling a timer for a deadline manager", e);
        //   }
        newJob().ofType(quatzWatchJob.class)
        .storeDurably()
        .withIdentity("Qrtz_Job_Detail")
        // .withDescription("Invoke Sample Job service...")
        .build();
        
        LocalDateTime ldt = LocalDateTime.now().plusSeconds(10);
        DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        String formatter = formmat1.format(ldt);
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        logger.info("Configuring trigger to fire every {} seconds",formatter);

        newTrigger().forJob(jobKey)
        .withIdentity(TriggerKey.triggerKey(GROUP_KEY_TRIGGER))
        // .withDescription("Sample trigger")
        .startAt(out)
        .build();

    }

}