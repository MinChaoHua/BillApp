package com.bill.system.service.impl;

import com.bill.system.dao.BilltypeMapper;
import com.bill.system.entity.Billtype;
import com.bill.system.service.BillTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class BillTypeServiceImpl implements BillTypeService {

    @Autowired
    private  HttpServletRequest request;

    @Resource
    BilltypeMapper billtypeMapper;

    @Override
    public List<Billtype> selectBilltype() {
        List<Billtype> billtypeList = billtypeMapper.selectAllBillType(request.getSession().getAttribute("userinfo").toString());
        return billtypeList;
    }

    @Override
    public Billtype selectBillTypeById(Integer billtypeId) {
        Billtype billtype = billtypeMapper.selectBillTypeById(request.getSession().getAttribute("userinfo").toString(),billtypeId);
        return billtype;
    }

    @Override
    public int insertBillType(Billtype billtype) {
        billtype.setAccountnumber(request.getSession().getAttribute("userinfo").toString());
        billtype.setAddtime(new Date());
        return  billtypeMapper.insert(billtype);
    }

    @Override
    public int deleteBillTypeByBillTypeId(int billId) {

        return billtypeMapper.deleteByPrimaryKey(billId);
    }


}
