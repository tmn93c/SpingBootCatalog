package com.example.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = { "com.example.demo.service" })
public class ConnectionManager {
    private static String url = "jdbc:mysql://localhost:3306/prototypeeop";    
    private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "tamnd";   
    private static String password = "tamnd";
    private static Connection con;
    private static String urlstring;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(urlstring, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }

    // Tạo bean dataSource
    @Bean
    public DriverManagerDataSource dataSource() throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        InputStream user_props = this.getClass()
                .getResourceAsStream("/application.properties");
        properties.load(user_props);
        dataSource.setDriverClassName(
                properties.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(properties.getProperty("spring.datasource.url"));
        dataSource.setUsername(
                properties.getProperty("spring.datasource.username"));
        dataSource.setPassword(
                properties.getProperty("spring.datasource.password"));
        return dataSource;
    }

    // đọc thông tin file cấu hình MyBatis
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        Resource resource = new ClassPathResource("sql.xml");
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(resource);
        return sqlSessionFactory;
    }
 
    // scan tất cả những mapper package vn.viettuts.mapper
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.example.demo.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }
}