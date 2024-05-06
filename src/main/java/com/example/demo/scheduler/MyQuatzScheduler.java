package com.example.demo.scheduler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import jakarta.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Configuration
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='true'")
public class MyQuatzScheduler {

    Logger logger = LoggerFactory.getLogger(getClass());
    final static String GROUP_KEY = "GROUP_QUATZ";
    final static String GROUP_KEY_TRIGGER = "GROUP_QUATZ_TRIGGER";
    private final Scheduler scheduler;
    public MyQuatzScheduler(
        Scheduler scheduler
    ) {
        this.scheduler = scheduler;
    }
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        logger.info("quatzScheduler from Quartz...");
    }
    @Scheduled(initialDelay = 1000 ,fixedDelay = 5000000)
    public void perDay1() {
        logger.info("A Day1 from Quartz...");
        runQuartz2();
    }

    // @Scheduled(initialDelay = 1000 ,fixedDelay = 5000000)
    public void perDay(String id) {
        logger.info("A Day from Quartz...");
        runQuartz(id);
    }
    //https://www.tabnine.com/code/java/methods/org.quartz.Scheduler/deleteJob
    public void runQuartz(String id){
        try {
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
		    JobKey jobKeyA = new JobKey("Qrtz_Job_Detail__"+id,GROUP_KEY);
		    createJob2(scheduler,jobKeyA,id);
		} catch (SchedulerException ex) {

		} catch (Exception ex) {

		}
    }

    public void runQuartz2(){
        try {
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
		} catch (SchedulerException ex) {

		} catch (Exception ex) {

		}
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
    public void createJob2(Scheduler scheduler,JobKey jobKey,String id) throws ParseException, SchedulerException { 
        JobDetail job = newJob(quatzWatchJob.class)
            .withIdentity("cronJob"+id, "testJob") 
            .build();

        String startDateStr = "2021-09-25 00:00:00";
        String endDateStr = "2021-09-25 16:18:00";

        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDateStr);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateStr);

        CronTrigger cronTrigger = newTrigger()
          .forJob(job)
          .usingJobData("id",id)
          .startAt(startDate)
          .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ?").withMisfireHandlingInstructionDoNothing())
          .endAt(endDate)
          .build();

        scheduler.scheduleJob(job, cronTrigger);
        scheduler.start();
    }
    public void createJob(Scheduler scheduler,JobKey jobKey) throws SchedulerException, ParseException {
        String startDateStr = "2021-09-25 00:00:00";
        String endDateStr = "2021-09-25 16:27:00";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDateStr);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateStr);
        JobDetail job = newJob(quatzWatchJob2.class)
            .withIdentity("cronJob2", "testJob") 
            .build();
        LocalDateTime ldt = LocalDateTime.now().plusSeconds(10);
        DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        String formatter = formmat1.format(ldt);
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        logger.info("Configuring trigger to fire every {} seconds",formatter);

        CronTrigger cronTrigger = newTrigger()
          .forJob(job)
          .startAt(startDate)
          .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ?").withMisfireHandlingInstructionDoNothing())
          .endAt(endDate)
          .build();

        scheduler.scheduleJob(job, cronTrigger);
        scheduler.start();

    }

}