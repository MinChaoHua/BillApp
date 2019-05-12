package com.bill.system.service.impl;

import com.bill.system.dao.UserMapper;
import com.bill.system.entity.UserWithBLOBs;
import com.bill.system.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {


    @Resource
    UserMapper userMapper;
    @Override
    public UserWithBLOBs getLonginUser(UserWithBLOBs userWithBLOBs) {

        UserWithBLOBs userWithBLOBs1 = userMapper.selectLoginUser(userWithBLOBs.getAccountnumber(),userWithBLOBs.getPassword());
        return userWithBLOBs1;
    }
}
