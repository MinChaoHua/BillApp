package com.bill.system.controller;

import com.bill.system.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义错误返回的JSON 数据
 * */
@ControllerAdvice
public class MyExceptionHander {

    /**
     * 无法自适应，浏览器和客户端都是JSON数据
     * */
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String,Object> handlerException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","1000");
//        map.put("message",e.getMessage());
//        return map;
//    }

    /**
     * 自适应,转发到error自适应
     *
     * 注意：必须定义状态码，否则没法转发到自定义页面
     * */
    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("code","1000");
        map.put("message",e.getMessage());
        //设置状态码
        request.setAttribute("javax.servlet.error.status_code",500);

        //将自定义的数据放入请求域中
        request.setAttribute("extend",map);

        return "forward:/error";
    }

}
