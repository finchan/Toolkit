package com.xavier.toolkit.service.impl;

import com.xavier.toolkit.entity.SysUser1Extend;
import com.xavier.toolkit.entity.SysUserSimpleAssociation;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.xavier.toolkit.entity.SysUser1;
import com.xavier.toolkit.mapper.SysUser1Mapper;
import com.xavier.toolkit.service.SysUser1Service;

import java.util.List;

@Service
public class SysUser1ServiceImpl implements SysUser1Service{

    @Resource SysUser1Mapper sysUser1Mapper;

    public Long insertUserViaSEQ(SysUser1 user){
        return sysUser1Mapper.insertUserViaSEQ(user);
    }

    @Override
    public List<SysUser1> selectAllSysUsers() {
        return sysUser1Mapper.selectAllSysUsers();
    }

    @Override
    public List<SysUser1Extend> selectAllExtendedSysUser(Integer id) {
        return sysUser1Mapper.selectAllExtendedSysUser(id);
    }

    @Override
    public List<SysUserSimpleAssociation> selectSimpleAssociation(Integer id) {
        return sysUser1Mapper.selectSimpleAssociation(id);
    }

    @Override
    public Integer updateUserById(SysUser1 user) {
        return sysUser1Mapper.updateUserById(user);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        return sysUser1Mapper.deleteUserById(id);
    }
}
