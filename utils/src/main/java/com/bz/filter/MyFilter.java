package com.bz.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: THE GIFTED
 * @CreateTime 2019年07月29日 14:37
 */
@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入*-----------------------");
        HttpServletRequest req  = (HttpServletRequest) servletRequest;
        Cookie[] cookies = req.getCookies();
        String token = "";
        for (Cookie ck : cookies) {
            if(ck.getName().equals("TOK")){
                token = ck.getValue();
                break;
            }
        }
        if(token != ""){

        }

        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
