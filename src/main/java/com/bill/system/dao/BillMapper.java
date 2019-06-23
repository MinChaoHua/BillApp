package com.bill.system.dao;

import com.bill.system.entity.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BillMapper {
    int deleteByPrimaryKey(Integer billid);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Integer billid);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);

    List<Bill> selectBillListByType(@Param("accountNumber") String accountnumber,@Param("billType") String billtypename);

    //账单简略
    List<Bill> selectBriefBillList();

    //查找删除的账单
    List<Bill> selectByAccountnumberDeleteBill( String accountnumber);

    //恢复删除的账单
    int restoreByPrimaryKey(int id);

}