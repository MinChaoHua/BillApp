package com.bill.system.controller;

import com.bill.system.entity.UserWithBLOBs;
import com.bill.system.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

@Controller
public class LoginController {

    @Resource
    LoginService loginService;
    @RequestMapping("/successpage")
    public String getSuccess( Map<String,Object> map) {
        System.out.println("你好");
        map.put("hello","<h1>你好世界</h1>");
        map.put("users", Arrays.asList("张三","李四","王五"));
        return "success";
    }
    @PostMapping(value = "/tologin")
    public String toLogin(UserWithBLOBs userWithBLOBs,
                          Map<String,Object> map, HttpSession session){
        System.out.println("连接");
        UserWithBLOBs user= loginService.getLonginUser(userWithBLOBs);
        if(user!=null && !StringUtils.isEmpty(user.getAccountnumber())){
            session.setAttribute("userinfo",user.getNickname());
            return "redirect:/index";
        }else{
            map.put("msg","用户名或密码错误");
            return "login";
        }

    }
}
