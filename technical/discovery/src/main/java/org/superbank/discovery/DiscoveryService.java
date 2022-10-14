package org.superbank.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryService {
    public static void main(final String [] args) {
        SpringApplication.run(DiscoveryService.class, args);
    }
}
