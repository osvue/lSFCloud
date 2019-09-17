package com.bz.service.impl;

import com.bz.mapper.TBankMapper;
import com.bz.pojo.EAResult;
import com.bz.pojo.TBank;
import com.bz.pojo.TBankExample;
import com.bz.pojo.Users;
import com.bz.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {
    @Resource
    private TBankMapper tBankMapper;

    /**
     *
     * @param request
     * @return 高可用,获取当前的id下的卡
     */
    public List<TBank> hight(HttpServletRequest request){
        Users users = (Users) request.getSession().getAttribute("TOKEN");
        TBankExample example = new TBankExample();
//        根据id查询;
        example.createCriteria().andUserIdEqualTo(users.getUid());
        return tBankMapper.selectByExample(example);
    }

    @Override
    public EAResult show(HttpServletRequest request, int page, int rows) {
        PageHelper.startPage(page, rows);
//        大前题,已经登陆
        List<TBank> list = hight(request);
        PageInfo pi  = new PageInfo(list);
        EAResult result = new EAResult();
        result.setRows(pi.getList());
        result.setTotal((int) pi.getTotal());
//        为了导出数据的分类
        result.setPage(page);
        result.setInfo(rows);
        return result;
    }

    @Override
    public List<Map<String, Object>> getMyCart(HttpServletRequest request) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<TBank> list = hight(request);
        for (TBank tBank : list) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",tBank.getBankNo());
            map.put("text",tBank.getBankNo());
            result.add(map);
        }
        return result;
    }

    @Override
    public List<TBank> showAll() {
        return tBankMapper.selectByExample(new TBankExample());
    }
}
