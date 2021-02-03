package com.xavier.stamps.service;

import com.xavier.stamps.entity.Pager;
import com.xavier.stamps.entity.Stamp;

import java.util.List;

public interface StampsService {
    void insertStampInfo(Stamp stamp);

    String queryMaxID();

    Stamp getStampViaID(String id);

    String getMaxIDNum();

    Pager<List<Stamp>, Stamp> getStampsByPager(Pager<List<Stamp>, Stamp> pager);
}
