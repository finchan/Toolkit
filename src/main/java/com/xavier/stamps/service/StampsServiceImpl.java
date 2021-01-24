package com.xavier.stamps.service;

import com.xavier.stamps.entity.Stamp;
import com.xavier.stamps.mapper.StampsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class StampsServiceImpl implements StampsService {
    @Resource
    private StampsMapper stampsMapper;

    @Override
    public void insertStampInfo(Stamp stamp){
        stampsMapper.insertStampInfo(stamp);
    }

    @Override
    public String queryMaxID() {
        return stampsMapper.queryMaxID();
    }

    @Override
    public Stamp getStampViaID(String id) {
        return stampsMapper.getStampViaID(id);
    }
}
