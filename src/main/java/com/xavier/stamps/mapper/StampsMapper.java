package com.xavier.stamps.mapper;

import com.xavier.stamps.entity.Stamp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StampsMapper {
//    int deleteByPrimaryKey(String id);
//
//    int insert(Stamp record, String series, String id);
//
//    int insertSelective(Stamp record);
//
//    Stamp selectByPrimaryKey(String id);
//
//    int updateByPrimaryKeySelective(Stamp record);
//
//    int updateByPrimaryKey(Stamp record);

    void insertStampInfo(Stamp stamp);
}