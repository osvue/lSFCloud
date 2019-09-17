package com.bz.pojo;

import java.util.Date;

/**
 * 转账记录
 */
public class AccountDis {

     private  Integer  id     ;// int(10) PRIMARY key not null auto_increment,
     private  String  outId  ;// varchar(30) comment"转账人",
     private  String  outNum ;// varchar(30) comment '转账卡号',
     private  String  inId   ;//  VARCHAR(30),
     private  String  inNum  ;// VARCHAR(30),
     private  double  money  ;// DOUBLE comment'金额'
     private Date accdate;

    public Date getAccdate() {
        return accdate;
    }

    public void setAccdate(Date accdate) {
        this.accdate = accdate;
    }

    @Override
    public String toString() {
        return "AccountDis{" +
                "id=" + id +
                ", outId='" + outId + '\'' +
                ", outNum='" + outNum + '\'' +
                ", inId='" + inId + '\'' +
                ", inNum='" + inNum + '\'' +
                ", money=" + money +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public String getInId() {
        return inId;
    }

    public void setInId(String inId) {
        this.inId = inId;
    }

    public String getInNum() {
        return inNum;
    }

    public void setInNum(String inNum) {
        this.inNum = inNum;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
