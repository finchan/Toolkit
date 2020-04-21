package com.xavier.toolkit.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

import com.xavier.toolkit.entity.Regions;
import com.xavier.toolkit.mapper.RegionsMapper;
import com.xavier.toolkit.service.RegionsService;
@Service
public class RegionsServiceImpl implements RegionsService{

    @Resource
    private RegionsMapper regionsMapper;

    @Override
    public int deleteByPrimaryKey(BigDecimal regionId) {
        return regionsMapper.deleteByPrimaryKey(regionId);
    }

    @Override
    public int insert(Regions record) {
        return regionsMapper.insert(record);
    }

    @Override
    public int insertSelective(Regions record) {
        return regionsMapper.insertSelective(record);
    }

    @Override
    public Regions selectByPrimaryKey(BigDecimal regionId) {
        return regionsMapper.selectByPrimaryKey(regionId);
    }

    @Override
    public int updateByPrimaryKeySelective(Regions record) {
        return regionsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Regions record) {
        return regionsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Regions> findByAll() {
        return regionsMapper.findByAll();
    }
}
