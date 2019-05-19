package com.bill.system.service.impl;

import com.bill.system.dao.BillMapper;
import com.bill.system.dao.BilltypeMapper;
import com.bill.system.dao.BriefBillMapper;
import com.bill.system.entity.Bill;
import com.bill.system.entity.Billtype;
import com.bill.system.entity.BriefBill;
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

    @Resource
    BriefBillMapper briefBillMapper;

    @Override
    public List<Bill> selectBillListByType(String billtypename) {
        return billMapper.selectBillListByType(request.getSession().getAttribute("userinfo").toString(),billtypename);
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
        System.out.println("OK1");
        Billtype billtype = billtypeMapper.selectBillTypeById(accountNumber,Integer.valueOf(bill.getBilltype()));
        System.out.println("OK");
        System.out.printf(billtype.toString());
        bill.setBilltype(billtype.getBilltypename());

        //账单简略要在对应的类型下添加金额。
        //查看简略是否存在
        List<BriefBill> briefBillList = briefBillMapper.selectBriefBillList();
        List<String> briefBillType = new ArrayList<>();
        briefBillList.forEach((briefBill)-> briefBillType.add(briefBill.getBilltype()));

        //根据类型，名称查找
        BriefBill briefBill = briefBillMapper.selectBriefBillByTypeAndAccountNumber(accountNumber, billtype.getBilltypename());
        int totalBill = 0;
        if(briefBill!=null){
           totalBill = briefBill.getTotalbill() + bill.getTotalbill();
       }else{
            totalBill = bill.getTotalbill();
            briefBill = new BriefBill();
            briefBill.setAccountnumber(accountNumber);
            briefBill.setBilltype(bill.getBilltype());

       }
        briefBill.setTotalbill(totalBill);
        briefBill.setBriefbilldate(bill.getBilldate());
        int insertBriefBill = 0;
        if(briefBillType.indexOf(billtype.getBilltypename())==-1){//添加
            //添加
            insertBriefBill = briefBillMapper.insert(briefBill);
        }else{//修改
            insertBriefBill = briefBillMapper.updateByPrimaryKey(briefBill);
        }
        //添加账单
        int insertBill = billMapper.insert(bill);
        if(insertBriefBill>0 && insertBill>0){
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
    public int deleteBillByBillId(int billId) {
        return 0;
    }

    /**
     * @return List<BriefBill>
     * @deprecated  查询账单简略
     * */
    @Override
    public List<BriefBill> selectBriefBillList() {
        return  briefBillMapper.selectBriefBillList();
    }
}
