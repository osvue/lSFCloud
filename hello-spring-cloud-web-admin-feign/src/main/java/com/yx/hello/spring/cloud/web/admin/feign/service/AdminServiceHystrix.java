package com.yx.hello.spring.cloud.web.admin.feign.service;

import feign.Feign;
import org.springframework.stereotype.Component;

/**
 * 使用feign 做服务的熔断,
 */
@Component
public class AdminServiceHystrix implements FeignAdminService {
    @Override
    public String sayHi(String message) {
        return "Hi，熔断机制已启动,  your message is :\"" + message + "\" but request error.";
    }
}
