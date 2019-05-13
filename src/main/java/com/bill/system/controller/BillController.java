package com.bill.system.controller;

import com.bill.system.entity.Bill;
import com.bill.system.entity.Billtype;
import com.bill.system.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BillController {

    @Resource
    BillService billService;

    //查找账单类型
    @GetMapping(value = "/bill/selectBillList")
    public String getBillType(Map<String, Object> map) {
        List<Bill> billList = billService.selectBillList();
        map.put("billList", billList);
        return "bill/billList";
    }
    //添加账单
    @ResponseBody
    @PostMapping(value = "/bill/add", produces = "application/json;charset-utf-8")
    public Map<String, Object> addBillType(Bill bill) {
        System.out.println(bill.toString());
        HashMap<String, Object> map = new HashMap<>();
        int billNum = billService.insertBill(bill);
        if (billNum > 0) {
            map.put("resultBill", true);
            map.put("msg", "添加成功");
        } else {
            map.put("resultBill", false);
            map.put("msg", "添加失败");
        }
        return map;
    }

}
