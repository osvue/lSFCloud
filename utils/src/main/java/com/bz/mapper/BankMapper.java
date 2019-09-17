package com.bz.mapper;

import com.bz.pojo.Bank;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BankMapper {
    @Select("select * from bank ")
    List<Bank> selAll();

}
