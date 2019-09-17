package com.bz.controller;

import com.bz.util.ImgCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ImgCodeController {

    @RequestMapping("validcode")
    public void validcode(HttpServletRequest request, HttpServletResponse response){
        try {
            ImgCodeUtil.ImgCode(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
