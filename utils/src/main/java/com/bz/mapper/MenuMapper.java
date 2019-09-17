package com.bz.mapper;

import com.bz.pojo.Menu;

public interface MenuMapper {

    Menu selByPid(String pid);

    Menu selAll(String str);


}
