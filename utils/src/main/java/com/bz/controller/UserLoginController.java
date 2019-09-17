package com.bz.controller;

import com.bz.pojo.Users;
import com.bz.service.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆,注册
 */
@Controller
public class UserLoginController {
    @Resource
    private UserLoginService userLoginService;
    /**
     * 登陆
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping("login/user")
    public Map<String ,Object> login(Users users, String code, boolean three, HttpServletResponse resp, HttpServletRequest request){
//    验证码
        Object codes = request.getSession().getAttribute("code");
        if(codes.toString().equals(code)){
            return userLoginService.login(users,request,three,resp);
        }else {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("status", 5001);
            return hashMap;
        }
    }


}
