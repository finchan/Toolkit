package com.xavier.stamps.mapper;

import com.xavier.stamps.entity.Pager;
import com.xavier.stamps.entity.Stamp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StampsMapper {
//    int deleteByPrimaryKey(String id);

    void insertStampInfo(Stamp stamp);

    String queryMaxID();

    Stamp getStampViaID(String id);

    String getMaxIDNum();

    List<Stamp> getStampsByPager(Pager<List<Stamp>, Stamp> pager);
    Integer getStampsCountByPager(Pager<List<Stamp>, Stamp> pager);

    void deleteStamp(String id);
}