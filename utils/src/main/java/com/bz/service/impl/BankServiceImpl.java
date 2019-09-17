package com.bz.service.impl;

import com.bz.mapper.BankMapper;
import com.bz.pojo.Bank;
import com.bz.service.BankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class BankServiceImpl implements BankService {
    @Resource
    private BankMapper bankMapper;

    @Override
    public List<Bank> selectAll() {
        List<Bank> banks = bankMapper.selAll();
        return banks;
    }

}
