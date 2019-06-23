package com.bill.system.controller;

import com.bill.system.entity.Bill;
import com.bill.system.service.BillService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BillController {

    @Resource
    BillService billService;

    //查找账单
    @GetMapping(value = "/bill/selectBillList/{billType}")
    public String getBillType(@PathVariable("billType") String billtype , Map<String, Object> map) {
        List<Bill> billList = billService.selectBillListByType(billtype);
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

    //查找账单简略
    @GetMapping(value = "/bill/selectBillBriefList")
    public String getBillBrief(Map<String, Object> map) {
        List<Bill> briefBillList = billService.selectBriefBillList();
        map.put("briefBillList", briefBillList);
        return "bill/billBriefList";
    }

    //查找所有账单
    @GetMapping(value = "/bill/selectAllBillList")
    public String getAllBillList(Map<String, Object> map) {
        List<Bill> billList = billService.selectBillListByType(null);
        map.put("billList", billList);
        return "bill/allBillList";
    }

    //删除账单
    @ResponseBody
    @DeleteMapping(value = "/bill/delete/{id}", produces = "application/json;charset-utf-8")
    public Map<String, Object> deleteBillType(@PathVariable("id") int id) {
        System.out.println("删除");
        System.out.println("id="+id);
        Map<String, Object> map = new HashMap<>();
        int deleteBill = billService.deleteByPrimaryKey(id);
        if (deleteBill > 0) {
            map.put("deleteResult", true);
            map.put("msg", "删除成功");
        } else {
            map.put("deleteResult", false);
            map.put("msg", "删除失败");
        }
        return map;
    }

    /**
     *
     * 去删除恢复页面
     * */
    @GetMapping("/toRestoreBill")
    public String toRestoreBill(Map<String,List> map){
        map.put("billList",billService.selectByAccountnumberDelete());
        return "bill/restoreBill";
    }

    /**
     * 删除恢复
     * */
    @ResponseBody
    @PostMapping(value = "/restoreBill/{id}",produces = "application/json;charset-utf-8")
    public Map<String,Object>  restoreBill(@PathVariable("id")int id){

        Map<String,Object> map = new HashMap<>();
        int restorePhoto = billService.restoreByPrimaryKey(id);
        if (restorePhoto > 0) {
            map.put("restoreBill", true);
            map.put("msg", "恢复成功");
        } else {
            map.put("restoreBill", false);
            map.put("msg", "恢复失败");
        }
        return map;
    }

}
