package com.bill.system.service.impl;

import com.bill.system.dao.BillMapper;
import com.bill.system.entity.Bill;
import com.bill.system.service.BillService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Resource
    HttpServletRequest request;
    @Resource
    BillMapper billMapper;

    @Override
    public List<Bill> selectBillList() {
        return billMapper.selectBillList();
    }

    @Override
    public int insertBill(Bill bill) {
        bill.setAccountnumber(request.getSession().getAttribute("userinfo").toString());
        bill.setYear(new Date(bill.getBilldate().getYear()));
        bill.setMonth(String.valueOf(bill.getBilldate().getMonth()+1));
        System.out.println(bill.toString());
        return  billMapper.insert(bill);
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
