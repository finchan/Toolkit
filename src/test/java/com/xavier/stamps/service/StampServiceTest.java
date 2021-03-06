package com.xavier.stamps.service;

import com.xavier.stamps.entity.Stamp;
import com.xavier.stamps.utils.ParseHTML;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan({"com.xavier.stamps.mapper"})
@ComponentScan({"com.xavier.stamps.controller","com.xavier.stamps.service"})
public class StampServiceTest {
    @Autowired
    StampsService stampsService;

    @Test
    public void testInsertRecord() {
        String id = "CZE000003";
        String links = "https://colnect.com/en/stamps/stamp/121108-Edible_Frog_Rana_esculenta_Dwarf_Waterlily_Nymphaea_cand-Protection_of_Nature-Czechoslovakia";
        Stamp stamp = ParseHTML.generateEntireStamp(id, links);
//        Stamp stamp = new Stamp();
        stampsService.insertStampInfo(stamp);
    }
}


