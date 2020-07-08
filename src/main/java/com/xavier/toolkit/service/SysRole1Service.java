package com.xavier.toolkit.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xavier.toolkit.entity.SysRole1;
import org.apache.ibatis.annotations.Param;

public interface SysRole1Service{
    List<SysRole1> selectRoleByUserIdAndRoleEnabled(@Param("id") Integer id, @Param("enabled") Integer enabled);
    List<SysRole1> selectRoleByUserIdAndRoleEnabledMap(Map userRoleEnabled);
    SysRole1 selectById(Integer id);
    SysRole1 selectById2(Integer id);;
}
