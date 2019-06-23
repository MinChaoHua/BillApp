package com.bill.system.controller;

import com.bill.system.entity.UserWithBLOBs;
import com.bill.system.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
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
            session.setAttribute("userinfo",user.getAccountnumber());
            return "redirect:/index";
        }else{
            map.put("msg","用户名或密码错误");
            return "login";
        }


    }

    @GetMapping(value = "/toRegister")
    public String toRegister(){
       return "sign-up";
    }

    @ResponseBody
    @PostMapping(value = "/toSignUp")
    public Map<String,Object> getSignUp(UserWithBLOBs userWithBLOBs,@RequestParam("code") String code,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        System.out.println("code"+code);
        String  oldcode = (String) session.getAttribute(userWithBLOBs.getEmail());
        if(!oldcode.equals(code)){
            map.put("result",false);
            map.put("msg","验证码错误");

        }else{
            int insert = loginService.insert(userWithBLOBs);
            if(insert>0) {
                map.put("result", true);
                map.put("msg", "注册成功");
            }else if(insert==-1){
                map.put("result",false);
                map.put("msg","邮箱已注册");
            }else if(insert==-2){
                map.put("result",false);
                map.put("msg","用户名已存在");
            }else{
                map.put("result",false);
                map.put("msg","注册失败");
            }
        }
        return map;

    }

    @GetMapping("/quitUser")
    public String quitUser(){
        loginService.quitUser();
        return "login";
    }
}
