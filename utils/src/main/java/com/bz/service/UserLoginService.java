package com.bz.service;

import com.bz.pojo.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserLoginService {
    /**
     * 登陆
     * @param users
     * @param request
     * @return
     */
    Map<String,Object> login(Users users, HttpServletRequest request, boolean three, HttpServletResponse resp);
}
