package com.bz.service.impl;

import com.bz.mapper.AccountDisMapper;
import com.bz.pojo.AccountDis;
import com.bz.pojo.EAResult;
import com.bz.service.AccountDisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountDisServiceImpl implements AccountDisService {
    @Resource
    private AccountDisMapper accountDisMapper;

    @Override
    public EAResult selAll() {
        List<AccountDis> accountDis = accountDisMapper.selAll();
        EAResult result = new EAResult();
        result.setRows(accountDis);

        return result;
    }

    @Override
    public int insertAccountDis(AccountDis accountDis) {
        return accountDisMapper.insertAccount(accountDis);
    }
}
