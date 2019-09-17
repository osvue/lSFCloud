package com.yx.hello.spring.cloud.web.admin.feign.controller;

import com.yx.hello.spring.cloud.web.admin.feign.service.FeignAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignAdminController {
    @Autowired
    private FeignAdminService adminService;

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    public String sayHi(@RequestParam String message) {
//        String.for
        return adminService.sayHi(message);
    }
}
