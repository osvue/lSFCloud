package com.bz.pojo;

public class Bank {

    private Integer id;
    private Integer bankId;
    private String bankName;
    private String nextNum;

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", nextNum='" + nextNum + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getNextNum() {
        return nextNum;
    }

    public void setNextNum(String nextNum) {
        this.nextNum = nextNum;
    }
}
