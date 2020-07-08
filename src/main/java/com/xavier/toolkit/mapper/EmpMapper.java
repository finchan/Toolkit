package com.xavier.toolkit.mapper;

import com.xavier.toolkit.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    int deleteByPrimaryKey(Integer employeeId);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Integer employeeId);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

    void batchInsert(@Param("empList") List<Emp> empList);
}