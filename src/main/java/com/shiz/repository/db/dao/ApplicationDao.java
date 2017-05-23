package com.shiz.repository.db.dao;

import com.shiz.entity.AppEntity;
import com.shiz.model.data.event.InstallApp;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by oldman on 22.04.17.
 */
public interface ApplicationDao {
    void addAppsList(int deviceId, List<InstallApp> appEntities) throws Exception;
    List<InstallApp> getAppEntityList(int deviceId) throws Exception;
}
