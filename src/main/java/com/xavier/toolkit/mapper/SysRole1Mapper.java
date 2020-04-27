package com.xavier.toolkit.mapper;

import com.xavier.toolkit.entity.SysRole1;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SysRole1Mapper {
    List<SysRole1> selectRoleByUserIdAndRoleEnabled(@Param("id") Integer id, @Param("enabled") Integer enabled);
    List<SysRole1> selectRoleByUserIdAndRoleEnabledMap(Map userRoleEnabled);
}