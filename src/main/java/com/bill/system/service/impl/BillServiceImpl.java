package com.bill.system.service.impl;

import com.bill.system.dao.BillMapper;
import com.bill.system.entity.Bill;
import com.bill.system.service.BillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {


    @Resource
    BillMapper billMapper;

    @Override
    public List<Bill> selectBillList() {
        return billMapper.selectBillList();
    }

    @Override
    public int insertBill(Bill bill) {
        return 0;
    }

    @Override
    public int updateBillByBillId(int billId, Bill bill) {
        return 0;
    }

    @Override
    public int deleteBillByBillId(int billId) {
        return 0;
    }
}
