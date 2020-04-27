package com.xavier.toolkit.service;

import com.xavier.toolkit.entity.SysRole1;
import com.xavier.toolkit.entity.SysUser1;
import com.xavier.toolkit.entity.SysUser1Extend;
import com.xavier.toolkit.entity.SysUserSimpleAssociation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan({"com.xavier.toolkit.mapper", "com.xavier.toolkit.dao"})
public class ServiceTest {
    @Autowired SysUser1Service  sysUser1Service;
    @Autowired SysRole1Service sysRole1Service;

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
}
