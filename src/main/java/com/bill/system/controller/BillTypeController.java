package com.bill.system.controller;

import com.bill.system.entity.Billtype;
import com.bill.system.service.BillTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BillTypeController { // 获取账单类型

    @Resource
    BillTypeService billService;

    //查找账单类型
    @GetMapping(value = "/billType/select")
    public String getBillType(Map<String, Object> map) {
        List<Billtype> billtypeList = billService.selectBilltype();
        map.put("billtypeList", billtypeList);
        return "bill/bill-type";
    }

    //去账单类型设置页面
    @GetMapping("/billType/Setting")
    public String toBillTypeSetting(Map<String, Object> map) {
        List<Billtype> billtypeList = billService.selectBilltype();
        map.put("billtypeList", billtypeList);
        return "bill/bill-type-setting.html";
    }

    //去添加账单类型页面
    @GetMapping("/billType/toAddBillType")
    public String toAddBillType() {
        return "bill/add-bill-type";
    }
    //添加账单类型
    @ResponseBody
    @PostMapping(value = "/billType/add",produces = "application/json;charset-utf-8")
    public Map<String,Object> addBillType( Billtype billtype) {
        HashMap<String, Object> map = new HashMap<>();
        int billtypeNum = billService.insertBillType(billtype);
        if (billtypeNum > 0) {
            map.put("result", true);
            map.put("msg", "添加成功");
        } else {
            map.put("result", false);
            map.put("msg", "添加失败");
        }
        return map;
    }


    //删除账单类型
    @ResponseBody
    @DeleteMapping("/billType/delete/{id}")
    public  Map<String,Object> deleteBillType(@PathVariable("id") int billtypeId){
        Map<String,Object> map = new HashMap<>();
        int billtypeNum = billService.deleteBillTypeByBillTypeId(billtypeId);
        if(billtypeNum>0){
            map.put("result",true);
            map.put("msg","删除成功");

        }else{
            map.put("result",false);
            map.put("msg","删除失败");
        }
        return map;
    }
}
//
//    //修改账单类型
//    @PostMapping(value = "/addBillType")
//    public String updateBillType(Map<String,Object> map,int billtypeId ,Billtype billtype){
//        int billtypeNum = billService.updateBillTypeByBillTypeId(billtypeId,billtype);
//        if(billtypeNum>0){
//            map.put("true","添加成功");
//        }else{
//            map.put("false","添加失败");
//        }
//        return "bill/add-bill-type";
//    }
