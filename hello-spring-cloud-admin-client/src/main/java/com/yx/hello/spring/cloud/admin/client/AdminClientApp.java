package com.yx.hello.spring.cloud.admin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AdminClientApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminClientApp.class,args);
    }
}
