package com.xavier.toolkit.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.xavier.toolkit.entity.Emp;
import com.xavier.toolkit.mapper.EmpMapper;
import com.xavier.toolkit.service.EmpService;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService{

    @Resource
    private EmpMapper empMapper;

    @Override
    public int deleteByPrimaryKey(Integer employeeId) {
        return empMapper.deleteByPrimaryKey(employeeId);
    }

    @Override
    public int insert(Emp record) {
        return empMapper.insert(record);
    }

    @Override
    public int insertSelective(Emp record) {
        return empMapper.insertSelective(record);
    }

    @Override
    public Emp selectByPrimaryKey(Integer employeeId) {
        return empMapper.selectByPrimaryKey(employeeId);
    }

    @Override
    public int updateByPrimaryKeySelective(Emp record) {
        return empMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Emp record) {
        return empMapper.updateByPrimaryKey(record);
    }

    @Override
    public void batchInsert(List<Emp> empList) {
        empMapper.batchInsert(empList);
    }
}
