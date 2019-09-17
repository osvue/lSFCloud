package com.bz.service;

import com.bz.pojo.AccountDis;
import com.bz.pojo.EAResult;

public interface AccountDisService {

    EAResult selAll();

    int insertAccountDis(AccountDis accountDis);

}
