package com.example.demo.anotation;

import com.example.demo.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableRabbit
@Import({ RabbitMQConfig.class })
public @interface EnableRabbitMq {

}