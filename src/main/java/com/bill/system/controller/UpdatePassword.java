package com.bill.system.controller;

import com.bill.system.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UpdatePassword {

    @Resource
    LoginService loginService;


    @SuppressWarnings("Duplicates")
    @PostMapping("/updatePasswordByName")
    @ResponseBody
    public Map<String,Object> updatePassword(String password){
        Map<String, Object> map = new HashMap<>();

        int result = loginService.updatePassword(password, null);
        if (result > 0) {
            map.put("msg", "修改成功");
            map.put("result", true);
        }else{
            map.put("msg", "修改失败");
            map.put("result", false);
        }
        return map;
    }
}
