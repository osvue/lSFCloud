package com.bz.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor  {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object token = request.getSession().getAttribute("TOKEN");
        if(token != null ){
            return true;
        }
//        登陆放过
        StringBuffer url = request.getRequestURL();

        if(url.toString().endsWith("login") || url.toString().indexOf(".js")>-1 || url.toString().indexOf("login/user")>-1|| url.toString().indexOf("validcode")>-1){
            System.out.println(url);
            return true;
        }
        response.sendRedirect("/account/index.jsp");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
