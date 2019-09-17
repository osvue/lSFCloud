package com.bz.service.impl;

import com.bz.mapper.MenuMapper;
import com.bz.pojo.Menu;
import com.bz.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public Menu sel() {
        Menu menu = menuMapper.selAll("1001");
        System.out.println(menu);
        return menu;
    }
}
