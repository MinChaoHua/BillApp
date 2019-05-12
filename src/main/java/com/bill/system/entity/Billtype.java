package com.bill.system.entity;

import java.util.Date;

public class Billtype {
    private Integer billtypeid;

    private String billtypename;

    private Date addtime;

    private String accountnumber;

    private String memo;

    public Integer getBilltypeid() {
        return billtypeid;
    }

    public void setBilltypeid(Integer billtypeid) {
        this.billtypeid = billtypeid;
    }

    public String getBilltypename() {
        return billtypename;
    }

    public void setBilltypename(String billtypename) {
        this.billtypename = billtypename == null ? null : billtypename.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber == null ? null : accountnumber.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    @Override
    public String toString() {
        return "Billtype{" +
                "billtypeid=" + billtypeid +
                ", billtypename='" + billtypename + '\'' +
                ", addtime=" + addtime +
                ", accountnumber='" + accountnumber + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}