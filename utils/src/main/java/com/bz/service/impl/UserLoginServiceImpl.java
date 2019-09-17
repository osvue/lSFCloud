package com.bz.service.impl;

import com.bz.mapper.UsersMapper;
import com.bz.pojo.Users;
import com.bz.pojo.UsersExample;
import com.bz.service.UserLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Resource
    private UsersMapper usersMapper;


    @Override
    public Map<String, Object> login(Users users, HttpServletRequest request, boolean three, HttpServletResponse resp) {
        Map<String,Object> map = new HashMap<>();
        //根据用户查询密码
        UsersExample example = new UsersExample();
        example.createCriteria().andUnameEqualTo(users.getUname());
        List<Users> users1 = usersMapper.selectByExample(example);
        if(users1 != null && users1.size() >0){
//            MD5 加盐对比
            if(users1.get(0).getUpwd().equals(users.getUpwd())){
                map.put("status", 200);
                map.put("data", "登陆成功");
                request.getSession().setAttribute("TOKEN", users1.get(0));
//                三天免登陆
                if(three){
                    Cookie ck = new Cookie("TOK",users1.get(0).getUname());
                    ck.setMaxAge(1*60*60*24*3);
                    ck.setPath("/account");
                    resp.addCookie(ck);

                }
                return map;
            }
        }

        map.put("data", "ERROR,WARN");
        return map;
    }
}
