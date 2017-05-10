package com.shiz.repository.db.dao;

import com.shiz.entity.AppEntity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by oldman on 22.04.17.
 */
public interface ApplicationDao {
    void addAppsList(int deviceId, List<AppEntity> appEntities) throws Exception;
    List<AppEntity> getAppEntityList(int deviceId) throws Exception;
}
