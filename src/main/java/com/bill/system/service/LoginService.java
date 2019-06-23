package com.bill.system.service;

import com.bill.system.entity.UserWithBLOBs;

public interface LoginService {

    UserWithBLOBs getLonginUser(UserWithBLOBs userWithBLOBs);
    /**
     * 修改密码*/
    int updatePassword(String password,String email);

    /**
     * 添加用户
     * */
    int insert(UserWithBLOBs userWithBLOBs);

    /**
     * 退出
     * */
    void quitUser();
}
