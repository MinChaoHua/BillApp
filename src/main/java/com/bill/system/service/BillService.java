package com.bill.system.service;

import com.bill.system.entity.Bill;

import java.util.List;

public interface BillService {

    //查找账单
    List<Bill> selectBillListByType(String billtypename);

    //查找删除账单
    List<Bill> selectByAccountnumberDelete();

    //添加账单
    int insertBill(Bill bill);

    //修改账单
    int updateBillByBillId(int billId,Bill bill);

    //删除账单
    int deleteByPrimaryKey(int billId);

    //查询账单简略

    List<Bill> selectBriefBillList();

    //恢复删除的账单
    int restoreByPrimaryKey(int id);
}
