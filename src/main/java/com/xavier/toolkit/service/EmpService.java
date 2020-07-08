package com.xavier.toolkit.service;

import com.xavier.toolkit.entity.Emp;

import java.util.List;

public interface EmpService{


    int deleteByPrimaryKey(Integer employeeId);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Integer employeeId);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

    void batchInsert(List<Emp> empList);
}
