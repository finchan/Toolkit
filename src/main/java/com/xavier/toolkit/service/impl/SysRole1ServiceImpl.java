package com.xavier.toolkit.service.impl;

import com.xavier.toolkit.mapper.SysRole2Mapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xavier.toolkit.mapper.SysRole1Mapper;
import com.xavier.toolkit.entity.SysRole1;
import com.xavier.toolkit.service.SysRole1Service;
@Service
public class SysRole1ServiceImpl implements SysRole1Service{
    @Resource
    private SysRole1Mapper sysRole1Mapper;

    @Resource
    private SysRole2Mapper sysRole2Mapper;

    @Override
    public List<SysRole1> selectRoleByUserIdAndRoleEnabled(Integer id, Integer enabled) {
        return sysRole1Mapper.selectRoleByUserIdAndRoleEnabled(id, enabled);
    }

    @Override
    public List<SysRole1> selectRoleByUserIdAndRoleEnabledMap(Map userRoleEnabled) {
        return sysRole1Mapper.selectRoleByUserIdAndRoleEnabledMap(userRoleEnabled);
    }

    @Override
    public SysRole1 selectById(Integer id) {
        return sysRole2Mapper.selectById(id);
    }

    @Override
    public SysRole1 selectById2(Integer id) {
        return sysRole2Mapper.selectById2(id);
    }
}
