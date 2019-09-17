package com.bz.controller;

import com.bz.pojo.Bank;
import com.bz.service.BankService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class BankController {
    @Resource
    private BankService bankServiceImpl;
    @ResponseBody
    @RequestMapping("/bank/getBank")
    public List<Bank> selAll(){
        return bankServiceImpl.selectAll();
    }




}

