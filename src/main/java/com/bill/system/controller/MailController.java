package com.bill.system.controller;

import com.bill.system.service.LoginService;
import com.bill.system.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Controller
public class MailController {

    @Resource
    MailService mailService;

    @Resource
    LoginService loginService;

    @PostMapping(value = "/sendCode")
    public String sendCode(String email, HttpSession session){
        String code =String.valueOf((int)(Math.random()*41115)+99999);
        mailService.sendEmail("验证码",code,email);
        session.setAttribute(email,code);
        // 加密字符串
        return "forgetPassword";
    }

    @SuppressWarnings("Duplicates")
    @ResponseBody
    @PostMapping(value = "/updatePassword")
    public Map<String,Object> signUp(@RequestParam("code")String code,@RequestParam("password")String password, @RequestParam("email")String email,HttpSession session)

    {
        Map<String, Object> map = new HashMap<>();
        String oldCode = (String)session.getAttribute(email);
        if(!oldCode.equals(code)){
            map.put("msg","验证码错误");
            map.put("result",false);
        }else {
            //对密码进行 md5 加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            int num = loginService.updatePassword(md5Password, email);
            System.out.println(md5Password+"你好");
            if (num > 0) {
                map.put("msg", "修改成功");
                map.put("result", true);
            }else{
                map.put("msg", "修改失败");
                map.put("result", false);
            }
        }
        return  map;
    }
}
