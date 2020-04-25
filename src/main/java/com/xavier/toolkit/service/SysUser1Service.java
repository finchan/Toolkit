package com.xavier.toolkit.service;

import com.xavier.toolkit.entity.SysUser1;
import com.xavier.toolkit.entity.SysUser1Extend;
import com.xavier.toolkit.entity.SysUserSimpleAssociation;

import java.util.List;

public interface SysUser1Service{
    Long insertUserViaSEQ(SysUser1 user);
    List<SysUser1> selectAllSysUsers();
    List<SysUser1Extend> selectAllExtendedSysUser(Integer id);
    List<SysUserSimpleAssociation> selectSimpleAssociation(Integer id);
    Integer updateUserById(SysUser1 user);
    Integer deleteUserById(Integer id);
}
