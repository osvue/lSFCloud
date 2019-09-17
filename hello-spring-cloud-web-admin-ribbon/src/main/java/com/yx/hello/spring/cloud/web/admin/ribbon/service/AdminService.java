package com.yx.hello.spring.cloud.web.admin.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*直接用的程序名替代了具体的 URL 地址，
在 Ribbon 中它会根据服务名来选择具体的服务实例，
根据服务实例在请求的时候会用具体的 URL 替换掉服务名
* */
@Service
public class AdminService {
    @Autowired
    private RestTemplate restTemplate;
//    在 Service 中增加 @HystrixCommand 注解
    @HystrixCommand(fallbackMethod = "backError")
    public String sayHi(String message) {
        return restTemplate.getForObject("http://HELLO-SPRING-CLOUD-SERVICE-ADMIN/hi?message=" + message, String.class);
    }
    public String backError(String msg){
         return "Hi，your message is :\"" + msg + "\" but request ERROR";
    }
}
