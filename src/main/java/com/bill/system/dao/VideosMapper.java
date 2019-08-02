package com.bill.system.dao;

import com.bill.system.entity.Videos;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideosMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Videos record);

    int insertSelective(Videos record);

    Videos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Videos record);

    int updateByPrimaryKey(Videos record);

    List<Videos> selectAll();
}