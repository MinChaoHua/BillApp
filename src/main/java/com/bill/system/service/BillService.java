package com.bill.system.service;

import com.bill.system.entity.Bill;

import java.util.List;

public interface BillService {

    //查找账单类型
    List<Bill> selectBillList();

    //添加账单类型
    int insertBill(Bill bill);

    //修改账单类型
    int updateBillByBillId(int billId,Bill bill);

    //删除账单类型
    int deleteBillByBillId(int billId);
}
