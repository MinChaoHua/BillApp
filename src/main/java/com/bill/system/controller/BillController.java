package com.bill.system.controller;

import com.bill.system.entity.Bill;
import com.bill.system.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
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
        return "billList";
    }

}
