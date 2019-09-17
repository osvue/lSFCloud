package com.yx.hello.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer     //eureka 服务
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
/*Eureka 是一个高可用的组件，它没有后端缓存，
每一个实例注册之后需要向注册中心发送心跳（因此可以在内存中完成），
在默认情况下 Erureka Server 也是一个 Eureka Client ,必须要指定一个 Server。*/