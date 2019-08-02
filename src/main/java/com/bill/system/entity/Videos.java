package com.bill.system.entity;

import java.util.Date;

public class Videos {
    private Integer id;

    private String path;

    private String accoutnumber;

    private Date date;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getAccoutnumber() {
        return accoutnumber;
    }

    public void setAccoutnumber(String accoutnumber) {
        this.accoutnumber = accoutnumber == null ? null : accoutnumber.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}