package com.bill.system.service;

import com.bill.system.entity.Billtype;

import java.util.List;

public interface BillTypeService {
    //查找账单类型
    List<Billtype> selectBilltype();

    //添加账单类型
    int insertBillType(Billtype billtype);
//
//    //修改账单类型
//    int updateBillTypeByBillTypeId(int billId,Billtype billtype);
//
    //删除账单类型
    int deleteBillTypeByBillTypeId(int billId);
}
