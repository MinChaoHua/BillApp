package com.bill.system.service.impl;

import com.bill.system.dao.UserMapper;
import com.bill.system.entity.UserWithBLOBs;
import com.bill.system.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    HttpServletRequest request;

    @Resource
    UserMapper userMapper;
    @Override
    public UserWithBLOBs getLonginUser(UserWithBLOBs userWithBLOBs) {

        //对密码进行 md5 加密
        String password = DigestUtils.md5DigestAsHex(userWithBLOBs.getPassword().getBytes());
        UserWithBLOBs userWithBLOBs1 = userMapper.selectLoginUser(userWithBLOBs.getAccountnumber(),password);
        return userWithBLOBs1;
    }

    @Override
    public int updatePassword(String password, String email) {
        if(email==null){
            return  userMapper.updatePassword(password, null,request.getSession().getAttribute("userinfo").toString());
        }else{
            return  userMapper.updatePassword(password, email,null);
        }
    }

    @Override
    public int insert(UserWithBLOBs userWithBLOBs) {
        userWithBLOBs.setDate(new Date());

        String email = userWithBLOBs.getEmail();
        UserWithBLOBs selectLByEmail = userMapper.selectLByEmail(email);
        UserWithBLOBs selectLByname = userMapper.selectLByname(userWithBLOBs.getAccountnumber());
        if(selectLByname!=null){
            return -2;
        }
        if(selectLByEmail==null){
            //对密码进行 md5 加密
            String md5Password = DigestUtils.md5DigestAsHex(userWithBLOBs.getPassword().getBytes());
            userWithBLOBs.setPassword(md5Password);
            return userMapper.insert(userWithBLOBs);
        }else{
            return -1;
        }
    }

    @Override
    public void quitUser() {
        request.getSession().invalidate();
    }
}
