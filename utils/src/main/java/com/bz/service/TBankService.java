package com.bz.service;

import com.bz.pojo.EAResult;
import com.bz.pojo.TBank;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 银行-用户
 */
public interface TBankService {
        //        根据卡号查询这条信息,判断卡号是否存在,如果存在判断持卡人是否一致,如果一致,第一张卡扣钱 第二张卡加钱,修改)
        String updAccountMoney(String myNum ,double money,String bankNum , String userName );

        String  getNextNum();
        EAResult insertTbank(TBank tBank, HttpServletRequest request);

        String updateTBank(String bankNum,Integer type, double money,int status);
//        显示比例
        List<Map<String,Object>> showVs(HttpServletRequest request);
        List<Map<String,Object>> showEveryOneCart();
        //查询最近7天
        List<Map<String,Object>> showSevenNewCart();

        Map<String ,Object> insImportAndInsert(InputStream ins,String filename);
}
