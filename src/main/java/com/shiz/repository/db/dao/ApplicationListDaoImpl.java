package com.shiz.repository.db.dao;

import com.shiz.entity.AppEntity;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by oldman on 22.04.17.
 */
public class ApplicationListDaoImpl implements ApplicationListDao {
    @Override
    public void addAppsList(Collection<AppEntity> item) throws SQLException, Exception {

    }

    @Override
    public Collection<AppEntity> getAllApps() throws SQLException, Exception {
        return null;
    }

    @Override
    public void deleteAllAppS() throws SQLException, Exception {

    }
}
