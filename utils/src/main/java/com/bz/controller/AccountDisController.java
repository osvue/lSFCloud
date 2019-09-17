package com.bz.controller;

import com.bz.pojo.EAResult;
import com.bz.service.AccountDisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class AccountDisController {
    @Resource
    private AccountDisService accountDisServiceImpl;
    @ResponseBody
    @RequestMapping("accdis/selall")
    public EAResult seleAll(){
        return accountDisServiceImpl.selAll();
    }

}
