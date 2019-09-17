package com.yx.hello.spring.cloud.web.admin.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Eureka Ribon 客户端负载轮询
 */
@SpringBootApplication
@EnableDiscoveryClient          //通过 @EnableDiscoveryClient 注解注册到服务中心
@EnableHystrix                  //在 Application 中增加 @EnableHystrix 注解 启用熔断
@EnableHystrixDashboard         // 开启服务熔断监控中心
public class WebAdminRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminRibbonApplication.class, args);
    }
}