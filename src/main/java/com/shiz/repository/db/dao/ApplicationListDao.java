package com.shiz.repository.db.dao;

import com.shiz.model.data.event.InstallApp;

import java.util.List;

/**
 * Created by oldman on 22.04.17.
 */
public interface ApplicationListDao {
    void addAppsList(int deviceId, List<InstallApp> appEntities) throws Exception;

    List<InstallApp> getAppEntityList(int deviceId) throws Exception;
}
