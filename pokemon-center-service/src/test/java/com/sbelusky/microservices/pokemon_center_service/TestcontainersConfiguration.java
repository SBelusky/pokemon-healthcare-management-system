package com.sbelusky.microservices.pokemon_center_service;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

@Configuration
@Testcontainers
class TestcontainersConfiguration {

@Bean
@ServiceConnection
    MongoDBContainer mongoDbContainer() {
        MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

        mongoDBContainer.withCopyFileToContainer(
            MountableFile.forClasspathResource("t_pokemon_centers.json"),
                "/docker-entrypoint-initdb.d/t_pokemon_centers.json"
        );

        mongoDBContainer.start();

        return mongoDBContainer;
    }
}
