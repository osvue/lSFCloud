package com.bz.mapper;

import com.bz.pojo.EAResult;
import com.bz.pojo.TBank;
import com.bz.pojo.TBankExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface TBankMapper {
    int countByExample(TBankExample example);

    int deleteByExample(TBankExample example);

    int deleteByPrimaryKey(Integer bankId);

    int insert(TBank record);
//    @Update("UPDATE t_bank set bank_money = (bank_money + #{param2} ) WHERE bank_no = #{param1}")
    int updateTBankMoney(String bankNum,Double money);

    int insertSelective(TBank record);

    List<TBank> selectByExample(TBankExample example);
    @Select("select max(bank_id) from t_bank")
    int selectMaxId();

    TBank selectByPrimaryKey(Integer bankId);



    /**
     * 显示比例
     * @return
     */
    @Select("select count(*) total ,bank_type type from t_bank GROUP BY bank_type")
    List<EAResult> showProportion();
    @Select("select count(*) total ,bank_name data from t_bank  GROUP BY bank_name  ")
    List<EAResult> showEveryOneCart();
    int updateByExampleSelective(@Param("record") TBank record, @Param("example") TBankExample example);

    int updateByExample(@Param("record") TBank record, @Param("example") TBankExample example);

    int updateByPrimaryKeySelective(TBank record);

    int updateByPrimaryKey(TBank record);
    /**
     * 时间日期查询
     * @param date
     * @return
     */
    List<EAResult> showSevenNewCart(Date date);

}