package com.xavier.toolkit.service;

import java.math.BigDecimal;
import java.util.List;

import com.xavier.toolkit.entity.Regions;
public interface RegionsService{


    int deleteByPrimaryKey(BigDecimal regionId);

    int insert(Regions record);

    int insertSelective(Regions record);

    Regions selectByPrimaryKey(BigDecimal regionId);

    int updateByPrimaryKeySelective(Regions record);

    int updateByPrimaryKey(Regions record);

    List<Regions> findByAll();

}
