package com.xavier.stamps.service;

import com.xavier.stamps.entity.Stamp;
import com.xavier.stamps.mapper.StampsMapper;
import com.xavier.stamps.service.StampsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class StampsService implements StampsService {
    @Resource
    private StampsMapper stampsMapper;

    @Override
    public void insertStampInfo(Stamp stamp) {
        stampsMapper.insertStampInfo(stamp);
    }
}
