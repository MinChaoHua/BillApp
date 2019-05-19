package com.bill.system.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
/**
 * 返回自定义字段
 * */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company","mch");//加入一个字段

        //获取的map就是自定义异常类的数据
        Map<String,Object> extend = ( Map<String,Object>)webRequest.getAttribute("extend", 0);

        map.put("extend",extend);
        return map;
    }
}
