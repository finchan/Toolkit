package com.xavier.toolkit.mapper;
import org.apache.ibatis.annotations.Param;

import com.xavier.toolkit.entity.Regions;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;
public interface RegionsMapper {
    int deleteByPrimaryKey(BigDecimal regionId);

    int insert(Regions record);

    int insertSelective(Regions record);

    Regions selectByPrimaryKey(BigDecimal regionId);

    int updateByPrimaryKeySelective(Regions record);

    int updateByPrimaryKey(Regions record);

    List<Regions>  findByAll( );

}