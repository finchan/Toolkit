package com.xavier.toolkit.service;

import com.xavier.toolkit.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan({"com.xavier.toolkit.mapper"})
public class ServiceTest {
    @Autowired SysUser1Service  sysUser1Service;
    @Autowired SysRole1Service sysRole1Service;
    @Autowired EmpService empService;

    @Test
    public void testSequenceInsert() {
        SysUser1 user = new SysUser1();
        user.setUserName("Xavier");
        user.setUserPassword("123");
        user.setUserInfo("Test");
        user.setHeadImg(new byte[] {1,2,3});
        user.setUserEmail("aaa@163.com");
        user.setCreateTime(new Date());

        sysUser1Service.insertUserViaSEQ(user);
    }

    @Test
    public void testAllUsers() {
        List<SysUser1> users = sysUser1Service.selectAllSysUsers();
        System.out.println(users.size());
    }

    @Test
    public void testExtendedUser() {
        List<SysUser1Extend> users = sysUser1Service.selectAllExtendedSysUser(1);
        System.out.println(users.size());
        for(SysUser1Extend user : users) {
            System.out.println(user.getUserName() + "\t" + user.getRoleName());
        }
    }

    @Test
    public void testUserSimpleAssociation() {
        List<SysUserSimpleAssociation> users = sysUser1Service.selectSimpleAssociation(1);
        System.out.println(users.size());
        for(SysUserSimpleAssociation user : users) {
            System.out.println(user.getUserName() + "\t" + user.getRole().getRoleName());
        }
    }

    @Test
    public void testUpdateUserById() {
        SysUser1 user = new SysUser1();
        user.setId(4);
        user.setHeadImg(new byte[]{2,3,4});
        user.setUserName("Javier");
        user.setCreateTime(new Date());
        Integer affectedRows = sysUser1Service.updateUserById(user);
        System.out.println(affectedRows);
    }

    @Test
    public void testDeleteUserById() {
        Integer affectedRows = sysUser1Service.deleteUserById(4);
        System.out.println(affectedRows);
    }

    @Test
    public void testMultipleParametersWithAnnotation() {
        List<SysRole1> roles = sysRole1Service.selectRoleByUserIdAndRoleEnabled(1,1);
        for(SysRole1 role: roles) {
            System.out.println(role.toString());
        }
    }

    @Test
    public void testMultipleParametersWithMap() {
        Map userRole = new HashMap();
        userRole.put("id", 1);
        userRole.put("enabled", 1);
        List<SysRole1> roles = sysRole1Service.selectRoleByUserIdAndRoleEnabledMap(userRole);
        for(SysRole1 role: roles) {
            System.out.println(role.toString());
        }
    }

    @Test
    public void testSelectAnnotation() {
        SysRole1 role = sysRole1Service.selectById(1);
        Assert.assertEquals("Administrator", role.getRoleName());
    }

    @Test
    public void testResults() {
        SysRole1 role = sysRole1Service.selectById2(1);
        Assert.assertEquals("Administrator", role.getRoleName());
    }

    @Test
    public void batchInsertTest() {
        List<Emp> empList = new ArrayList<Emp>();
        for(int i=0; i< 5; i++){
            Emp emp = new Emp();
            emp.setEmployeeId(i);
            emp.setEmail(i +"");
            emp.setFirstName(i +"");
            emp.setLastName(i+"");
            emp.setPhoneNumber(i+"");
            emp.setHireDate(new Date());
            emp.setSalary((double)i);
            emp.setCommissionPct(new BigDecimal(i).divide(new BigDecimal(10*(i/10+1))));
            empList.add(emp);
        }
        empService.batchInsert(empList);
    }

    @Test
    public void batchInsertEnhancementTest() {
        List<Emp> empList = new ArrayList<Emp>();
        for(int i=0; i< 5; i++){
            Emp emp = new Emp();
            emp.setEmployeeId(i);
            emp.setEmail(i +"");
            emp.setFirstName(i +"");
            emp.setLastName(i+"");
            emp.setPhoneNumber(i+"");
            emp.setHireDate(new Date());
            emp.setSalary((double)i);
            emp.setCommissionPct(new BigDecimal(i).divide(new BigDecimal(10*(i/10+1))));
            empList.add(emp);
        }
        for(Emp emp : empList) {
            try{
                empService.insertSelective(emp);
                System.out.println("INSERT SUCCESSFULLY - "  + emp);
            } catch (Exception ex) {
                String err = ((SQLIntegrityConstraintViolationException)((DuplicateKeyException)ex).getCause()).getMessage();
                if(err.contains("ORA-00001")) {
                    System.out.println("INSERT FAILED - 主键冲突 - " + "\t" + emp);
                } else{
                    System.out.println("INSERT FAILED - 其它错误 - " + "\t" + emp);
                }
            }
        }
    }
}
