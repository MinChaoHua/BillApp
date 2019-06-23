package com.bill.system.dao;

import com.bill.system.entity.Photos;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotosMapper {
    int deleteByPrimaryKey(Integer id);

    int restoreByPrimaryKey(Integer id);

    int insert(Photos record);

    int insertSelective(Photos record);

    Photos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Photos record);

    int updateByPrimaryKey(Photos record);

    List<Photos> selectByAccountnumber(String accountnumber);

    List<Photos> selectByAccountnumberDelete(String accountnumber);


}