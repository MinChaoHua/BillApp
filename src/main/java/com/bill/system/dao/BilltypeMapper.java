package com.bill.system.dao;

import com.bill.system.entity.Billtype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BilltypeMapper {
    int deleteByPrimaryKey(Integer billtypeid);

    int insert(Billtype record);

    int insertSelective(Billtype record);

    Billtype selectByPrimaryKey(Integer billtypeid);

    int updateByPrimaryKeySelective(Billtype record);

    int updateByPrimaryKey(Billtype record);

    List<Billtype> selectAllBillType( String accountnumber);

    Billtype selectBillTypeById(@Param("accountnumber") String accountnumber,@Param("billtypeid") Integer billtypeid);
}