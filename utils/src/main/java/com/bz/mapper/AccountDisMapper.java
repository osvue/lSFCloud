package com.bz.mapper;

import com.bz.pojo.AccountDis;
import com.bz.pojo.EAResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountDisMapper {

    @Select("select * from accountDis")
    List<AccountDis> selAll();
    @Insert("insert into accountDis values (default,#{outId},#{outNum},#{inId},#{inNum},#{money},#{accdate})")
    int insertAccount(AccountDis acc);


}
