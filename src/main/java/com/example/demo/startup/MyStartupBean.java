package com.example.demo.startup;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class MyStartupBean {
    private final ApplicationContext context;

    @PostConstruct
    public void init() throws InterruptedException {
        invokeMethodByName("mySchedulerTest", "checkVar");
        // Call your method or logic here
        // This code will be executed after all @Component beans are loaded
        System.out.println("All @Component beans are loaded, executing startup logic!");
        // Call your method or logic here
    }

    private void invokeMethodByName(String beanName, String methodName, Object... args) {
        try {
            Object bean = context.getBean(beanName);
            Method method = bean.getClass().getMethod(methodName, getParameterTypes(args));
            method.invoke(bean, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Class<?>[] getParameterTypes(Object... args) {
        return args == null ? new Class<?>[0] : Arrays.stream(args)
                .map(Object::getClass)
                .toArray(Class<?>[]::new);
    }
}