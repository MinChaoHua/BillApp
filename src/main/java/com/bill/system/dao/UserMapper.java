package com.bill.system.dao;

import com.bill.system.entity.User;
import com.bill.system.entity.UserWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String accountnumber);

    UserWithBLOBs selectByPrimaryKey(String accountnumber);

    int updateByPrimaryKeySelective(UserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserWithBLOBs record);

    int updateByPrimaryKey(User record);

    int insert(UserWithBLOBs record);

    UserWithBLOBs selectLoginUser(@Param("accountnumber") String accountnumber,@Param("password") String password);

    int updatePassword(@Param("password")String password,@Param("email")String eamil);
}