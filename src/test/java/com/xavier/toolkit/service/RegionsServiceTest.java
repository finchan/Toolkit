package com.xavier.toolkit.service;

import com.xavier.toolkit.entity.Regions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan({"com.xavier.toolkit.mapper", "com.xavier.toolkit.dao"})
public class RegionsServiceTest {

    @Autowired RegionsService regionsService;

    @Test
    public void getTheFirstRegion() {
        Regions region = regionsService.selectByPrimaryKey(new BigDecimal(1));
        Assert.assertEquals("Europe", region.getRegionName());
    }

    @Test
    public void findByAll() {
        List<Regions> regionsList = regionsService.findByAll();
        for(Regions region : regionsList) {
            System.out.println(region.getRegionName());
        }
    }
}
