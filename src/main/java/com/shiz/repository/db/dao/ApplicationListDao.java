package com.shiz.repository.db.dao;

import com.shiz.entity.AppEntity;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by oldman on 22.04.17.
 */
public interface ApplicationListDao {
    public void addAppsList(Collection<AppEntity> item) throws SQLException, Exception;
    public Collection<AppEntity> getAllApps() throws SQLException, Exception;
    public void deleteAllAppS() throws SQLException, Exception;
}
