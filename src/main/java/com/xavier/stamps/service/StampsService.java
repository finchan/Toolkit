package com.xavier.stamps.service;

import com.xavier.stamps.entity.Stamp;

public interface StampsService {
    void insertStampInfo(Stamp stamp);

    String queryMaxID();

    Stamp getStampViaID(String id);

    String getMaxIDNum();
}
