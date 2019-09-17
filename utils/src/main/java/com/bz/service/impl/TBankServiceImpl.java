package com.bz.service.impl;

import com.bz.mapper.AccountDisMapper;
import com.bz.mapper.TBankMapper;
import com.bz.mapper.UsersMapper;
import com.bz.pojo.*;
import com.bz.service.TBankService;
import com.bz.util.DateUtil;
import com.bz.util.ImportUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TBankServiceImpl implements TBankService {
    @Resource
    private TBankMapper tBankMapper;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private AccountDisMapper accountDisMapper;
    @Override
    public String updAccountMoney(String myNum, double money, String bankNum, String userName) {
//  根据卡号查询这条信息,判断卡号是否存在,如果存在判断持卡人是否一致,如果一致,第一张卡扣钱 第二张卡加钱,修改
        TBankExample example = new TBankExample();
        example.createCriteria().andBankNoEqualTo(bankNum);
        List<TBank> list = tBankMapper.selectByExample(example);
//        卡号存在
        if(list != null && list.size() > 0) {
//        用户存在
            Integer userId = list.get(0).getUserId();
            Users users = usersMapper.selectByPrimaryKey(userId);
            if(users.getUname().equals(userName)){
                TBankExample tBankExample = new TBankExample();
//                查询我的卡号
                tBankExample.createCriteria().andBankNoEqualTo(myNum);
                List<TBank> myTBanks = tBankMapper.selectByExample(tBankExample);
//                余额足够
                int index = 0;
                TBank tBank = myTBanks.get(0);
                if(tBank.getBankMoney() > money ){
                    index += tBankMapper.updateTBankMoney(myNum, ((-1) * money));
                    index += tBankMapper.updateTBankMoney(bankNum, money);
                    //                转账成功
                    if(index == 2){
                        AccountDis acc = new AccountDis();
                        acc.setOutId(usersMapper.selectByPrimaryKey(tBank.getUserId()).getUname());
                        acc.setOutNum(myNum);
                        acc.setMoney(money);
                        acc.setInId(userName);
                        acc.setInNum(bankNum);
                        acc.setAccdate(new Date());
                        index += accountDisMapper.insertAccount(acc);
                        if(index  == 3){
                            return "200";
                        }else{
                            return "200";
                        }
                    }else {
                        return "5001";
                    }
                }else {
                    return "余额不足";
                }
            }else {
                return "用户不存在";
            }
        }else {
            return "对方卡号不存在";
        }
    }

    @Override
    public String getNextNum() {
//        查询最大的id
//        根据id 查询 银行卡号+1
        int maxId = tBankMapper.selectMaxId();
        TBank tBank = tBankMapper.selectByPrimaryKey(maxId);
        String bankNo = tBank.getBankNo();
        long aLong = Long.parseLong(bankNo);
        aLong+=1;
        return new Long(aLong).toString();
    }

    @Override
    public EAResult insertTbank(TBank tBank, HttpServletRequest request) {
        EAResult result = new EAResult();
        tBank.setBankDate(new Date());
        Users user = (Users) request.getSession().getAttribute("TOKEN");
        tBank.setUserId(user.getUid());
        int index = tBankMapper.insertSelective(tBank);
        if(index >0){
            result.setStatus(200);
            return result;
        }
        return result;
    }
    //* @param status 1 存还款 /0 取款
    @Override
    public String updateTBank(String bankNum, Integer type, double money,int status) {
        TBankExample example = new TBankExample();
//        根据卡号查询 银行卡
        example.createCriteria().andBankNoEqualTo(bankNum);
        List<TBank> list = tBankMapper.selectByExample(example);
//        获取唯一的银行卡
        TBank bank = list.get(0);
//        存还款
        if(status == 1){
            //后台应判断如果该卡种为储蓄卡,则直接让该账户的余额加上要存的钱数,
            if(type == 1){
                int index = tBankMapper.updateTBankMoney(bankNum, money);
                if(index > 0){
                    return "200";
                }else {
                    return "0";
                }
            }
//	如果卡种为信用卡,则判断还款的金额加上当前账户的余额如果大于20000则不能进行还款,否则直接让
//	该账户的余额加上要存的钱数
            if(type == 2){
                if(bank.getBankMoney()+money > 20000 ){
                    return "5001";
                }else {
                    int index = tBankMapper.updateTBankMoney(bankNum, money);
                    if(index > 0){
                        return "200";
                    }
                }
            }
            return "0";
        }else if(status == 0) {
//            /0 取款
//            判断账户余额是否高于要取款的金额,如果高于该金额,则之间让该账户的余额减去取款的金额,否则不做其他操作
                if(bank.getBankMoney() > money){
                    int index = tBankMapper.updateTBankMoney(bankNum, ((-1) * money));
                    if(index > 0){
                        return "200";
                    }
                }else {
                    return "5002";
                }
        }
        return null;
    }

    /**
     * 显示比例
     * @param request
     * @return
     */
    @Override
    public  List<Map<String,Object>> showVs(HttpServletRequest request) {
        List<EAResult> eaResults = tBankMapper.showProportion();
        List<Map<String,Object>> list =  new ArrayList<>();
        for (EAResult er : eaResults) {
            Map<String,Object> map = new HashMap<>();
            if(er.getType() == 1){
                map.put("value",er.getTotal());
                map.put("name","储蓄卡");
            }else {
                map.put("value",er.getTotal());
                map.put("name","信用卡");
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 查询每一个用户的卡
     * @return
     */
    @Override
    public List<Map<String, Object>> showEveryOneCart() {
        List<EAResult> eaResults = tBankMapper.showEveryOneCart();
        List<Map<String,Object>> list =  new ArrayList<>();
        for (EAResult er : eaResults) {
            Map<String,Object> map = new HashMap<>();
                //持卡人和数量
                map.put("name",er.getData().toString());
                map.put("value",er.getTotal());
            list.add(map);
        }
        return list;
    }

    /**
     * 获取过去7天的办卡人数
     * @return
     */
    @Override
    public List<Map<String, Object>> showSevenNewCart() {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sim.parse(DateUtil.getPastDate(7));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<EAResult> eaResults = tBankMapper.showSevenNewCart(date);
        List<Map<String,Object>> list =  new ArrayList<>();
//        获取过去7天的时间
        ArrayList<String> seven = DateUtil.test(7);
        for (int i = 0; i < seven.size() ; i++) {
            boolean flag = false;
            Map<String,Object> map = new HashMap<>();
            for (EAResult er : eaResults) {
                String format = sim.format(er.getData());
                if(seven.get(i).equals(format)){
                    map.put("time",format);
                    map.put("value",er.getTotal());
                    list.add(map);
                    flag = true;
                    break;
                }
            }
            if(!flag){
                map.put("time",seven.get(i));
                map.put("value",0);
                list.add(map);
            }
        }

        return list;
    }

    @Override
    public Map<String, Object> insImportAndInsert(InputStream ins,String filename) {
        Map <String,Object> map = new HashMap<>();
        try {
            List<List<Object>> list = ImportUtil.getBankListByExcel(ins, filename);
            System.out.println(list.size());
            int index = 0;
            for (List<Object> objects : list) {
                // 开户人	卡总类	银行
                Object[] po = objects.toArray(new Object[objects.size()]);
                System.out.println(Arrays.toString(po));
                    TBank tBank = new TBank();
                    String username = po[0].toString();
                    Integer cartType = null;
                    if(po[1].toString().indexOf("信用")>-1){
                        cartType  = 2;
                        tBank.setBankMoney(20000D);
                    }else if (po[1].toString().indexOf("储蓄")>-1){
                        cartType = 1;
                        tBank.setBankMoney(0D);
                    }else {
                        cartType = 0;
                        tBank.setBankMoney(0D);
                    }

                     Integer bank = null;
                    if(po[2].toString().indexOf("工商")>0){
                        bank  = 5;
                    }else if (po[2].toString().indexOf("澳洲")>-1){
                        bank = 1;
                    }else if(po[2].toString().indexOf("瑞士")>-1){
                        bank  = 2;
                    }else if (po[2].toString().indexOf("建设")>-1){
                        bank = 3;
                    }else if (po[2].toString().indexOf("邯郸")>-1){
                        bank = 4;
                    }else {
                        bank = 0;
                    }
                Date now = new Date();
                tBank.setBankDate(now);
                tBank.setBankType(cartType);
                tBank.setBankBelong(bank);
                tBank.setBankName(username);
                tBank.setBankNo(getNextNum());
                UsersExample example = new UsersExample();
                example.createCriteria().andUnameEqualTo(username);
                tBank.setUserId(usersMapper.selectByExample(example).get(0).getUid());
                index += tBankMapper.insertSelective(tBank);
            }
            if(index == list.size()){
                map.put("status",200);
            }else {
                map.put("status",202);
                map.put("WARN","新增了"+index+"条");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }


}
