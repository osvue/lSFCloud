package com.bz.controller;

import com.bz.pojo.Menu;
import com.bz.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MenuController {
    @Resource
    private MenuService menuServiceImpl;
    @ResponseBody
    @RequestMapping("springBoot")
    public Menu selAll(){
        return menuServiceImpl.sel();
    }
}
