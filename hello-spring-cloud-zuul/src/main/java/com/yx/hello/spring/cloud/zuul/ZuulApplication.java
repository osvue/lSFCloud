package com.yx.hello.spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 服务网关 zuul
 https://github.com/ZXinYu2987/takeOutTwo.git

 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy        //启用网关代理 路由
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}