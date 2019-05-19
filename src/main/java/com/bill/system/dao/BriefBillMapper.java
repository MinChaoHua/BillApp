package com.bill.system.dao;

import com.bill.system.entity.BriefBill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BriefBillMapper {
    int deleteByPrimaryKey(Integer briefbillid);

    int insert(BriefBill record);

    int insertSelective(BriefBill record);

    BriefBill selectByPrimaryKey(Integer briefbillid);

    int updateByPrimaryKeySelective(BriefBill record);

    int updateByPrimaryKey( BriefBill record);

    List<BriefBill> selectBriefBillList();

    BriefBill selectBriefBillByTypeAndAccountNumber(@Param("accountnumber") String accountnumber , @Param("billType") String billtype);


}