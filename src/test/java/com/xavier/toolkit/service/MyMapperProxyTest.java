package com.xavier.toolkit.service;

import com.xavier.toolkit.entity.SysUser1;
import com.xavier.toolkit.mapper.SysUser1Mapper;
import com.xavier.toolkit.mapper.impl.MyMapperProxy;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyMapperProxyTest {
    @Autowired
    SqlSessionFactory factory;

    private SqlSession sqlSession;

    @Before
    public void init() {
        sqlSession = factory.openSession();
    }

    @Test
    public void testSelectAllUsers() {
        MyMapperProxy userMapperProxy = new MyMapperProxy(SysUser1Mapper.class, sqlSession);
        SysUser1Mapper userMapper  = (SysUser1Mapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{SysUser1Mapper.class},
                userMapperProxy);
        List<SysUser1> users = userMapper.selectAllSysUsers();
        System.out.println(users.size());
    }

    @After
    public void closeSession() {
        sqlSession.close();
    }
}
