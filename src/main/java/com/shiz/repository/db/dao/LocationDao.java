package com.shiz.repository.db.dao;

import com.shiz.model.data.event.Location;

import java.util.List;

/**
 * Created by OldMan on 10.05.2017.
 */
public interface LocationDao {
    void addLocationList(int deviceId, List<Location> locationList) throws Exception;

    List<Location> getLocationList(int deviceId) throws Exception;

}
