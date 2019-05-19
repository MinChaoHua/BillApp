package com.bill.system.service;

import com.bill.system.entity.Bill;
import com.bill.system.entity.BriefBill;

import java.util.List;

public interface BillService {

    //查找账单
    List<Bill> selectBillListByType(String billtypename);

    //添加账单
    int insertBill(Bill bill);

    //修改账单
    int updateBillByBillId(int billId,Bill bill);

    //删除账单
    int deleteBillByBillId(int billId);

    //查询账单简略

    List<BriefBill> selectBriefBillList();
}
