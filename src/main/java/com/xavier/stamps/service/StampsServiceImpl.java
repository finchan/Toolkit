package com.xavier.stamps.service;

import com.xavier.stamps.entity.Pager;
import com.xavier.stamps.entity.Stamp;
import com.xavier.stamps.mapper.StampsMapper;
import com.xavier.stamps.utils.PagerConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public String getMaxIDNum() {
        return stampsMapper.getMaxIDNum();
    }

    @Override
    public Pager<List<Stamp>, Stamp> getStampsByPager(Pager<List<Stamp>, Stamp> pager) {
        Pager<List<Stamp>, Stamp> pagerResult = new Pager<>();
        Integer counts = stampsMapper.getStampsCountByPager(pager);
        pager.setSize(PagerConstants.INITIALPAGERSIZE);
        List<Stamp> stampList = stampsMapper.getStampsByPager(pager);

        pager.setTotal(counts);
        pager.setEntities(stampList);
        return pager;
    }
}
