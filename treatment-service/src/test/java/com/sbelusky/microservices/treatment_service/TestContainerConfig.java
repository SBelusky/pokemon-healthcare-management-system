package com.sbelusky.microservices.treatment_service;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Configuration(proxyBeanMethods = false)
@Testcontainers
class TestContainerConfig {
    @Bean
    @ServiceConnection
    public MySQLContainer<?> mySQLContainer() {
        MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.19")
                .withDatabaseName("treatment")
                .withUsername("root")
                .withPassword("password")
                .withInitScript("mysql/init.sql");

        System.out.println("Starting MySQL container...");
        mySQLContainer.start();

        return mySQLContainer;
    }

}
