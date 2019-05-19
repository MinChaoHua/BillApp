package com.bill.system.entity;

import java.util.Date;

public class BriefBill {
    private Integer briefbillid;

    private String billtype;

    private Integer totalbill;

    private String accountnumber;

    private Date briefbilldate;


    public Integer getBriefbillid() {
        return briefbillid;
    }

    public void setBriefbillid(Integer briefbillid) {
        this.briefbillid = briefbillid;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public Integer getTotalbill() {
        return totalbill;
    }

    public void setTotalbill(Integer totalbill) {
        this.totalbill = totalbill;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber == null ? null : accountnumber.trim();
    }

    public Date getBriefbilldate() {
        return briefbilldate;
    }

    public void setBriefbilldate(Date briefbilldate) {
        this.briefbilldate = briefbilldate;
    }

    @Override
    public String toString() {
        return "BriefBill{" +
                "briefbillid=" + briefbillid +
                ", billtype='" + billtype + '\'' +
                ", totalbill=" + totalbill +
                ", accountnumber='" + accountnumber + '\'' +
                ", briefbilldate=" + briefbilldate +'}';
    }
}