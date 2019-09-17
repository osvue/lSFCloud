package com.bz.service;

import com.bz.pojo.EAResult;
import com.bz.pojo.TBank;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UsersService {
    /**
     * 根据用户查询自己的信息
     * @param request
     * @param page
     * @param rows
     * @return
     */
        EAResult show(HttpServletRequest request , int page ,int rows);

        List<Map<String,Object>> getMyCart(HttpServletRequest request);

        List<TBank> showAll();

}
