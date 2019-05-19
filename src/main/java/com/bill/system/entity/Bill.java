package com.bill.system.entity;

import java.util.Date;

public class Bill {
    private Integer billid;

    private String billtype;

    private String accountnumber;

    private Date billdate;

    private Integer totalbill;

    private String memo;

    private String month;

    private String year;

    private Integer status;

    public Integer getBillid() {
        return billid;
    }

    public void setBillid(Integer billid) {
        this.billid = billid;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber == null ? null : accountnumber.trim();
    }

    public Date getBilldate() {
        return billdate;
    }

    public void setBilldate(Date billdate) {
        this.billdate = billdate;
    }

    public Integer getTotalbill() {
        return totalbill;
    }

    public void setTotalbill(Integer totalbill) {
        this.totalbill = totalbill;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billid=" + billid +
                ", billtype='" + billtype + '\'' +
                ", accountnumber='" + accountnumber + '\'' +
                ", billdate=" + billdate +
                ", totalbill=" + totalbill +
                ", memo='" + memo + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", status=" + status +
                '}';
    }
}