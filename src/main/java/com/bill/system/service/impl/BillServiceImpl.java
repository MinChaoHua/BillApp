package com.bill.system.service.impl;

import com.bill.system.dao.BillMapper;
import com.bill.system.dao.BilltypeMapper;
import com.bill.system.entity.Bill;
import com.bill.system.entity.Billtype;
import com.bill.system.service.BillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Resource
    HttpServletRequest request;
    @Resource
    BillMapper billMapper;

    @Resource
    BilltypeMapper billtypeMapper;

    @Override
    public List<Bill> selectBillListByType(String billtypename) {
        return billMapper.selectBillListByType(request.getSession().getAttribute("userinfo").toString(),billtypename);
    }

    @Override
    public List<Bill> selectByAccountnumberDelete() {

        return billMapper.selectByAccountnumberDeleteBill(request.getSession().getAttribute("userinfo").toString());
    }

    @Override
    public int insertBill(Bill bill) {
        String accountNumber = request.getSession().getAttribute("userinfo").toString();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bill.getBilldate());
        bill.setAccountnumber(accountNumber);
        bill.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
        bill.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));
        //根据账单类型Id查找名称
        Billtype billtype = billtypeMapper.selectBillTypeById(accountNumber,Integer.valueOf(bill.getBilltype()));
        bill.setBilltype(billtype.getBilltypename());
        bill.setStatus(1);
        //添加账单
        int insertBill = billMapper.insert(bill);
        if(insertBill>0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int updateBillByBillId(int billId, Bill bill) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(int billId) {

        return billMapper.deleteByPrimaryKey(billId);
    }

    @Override
    public List<Bill> selectBriefBillList() {
        return  billMapper.selectBriefBillList();
    }

    @Override
    public int restoreByPrimaryKey(int id) {
        return billMapper.restoreByPrimaryKey(id);
    }

}
