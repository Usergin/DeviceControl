package com.shiz.repository.db.dao;

import com.shiz.model.data.event.Location;

import java.util.Date;
import java.util.List;

/**
 * Created by OldMan on 10.05.2017.
 */
public interface LocationDao {
    void addLocationList(int deviceId, List<Location> locationList) throws Exception;

    List<Location> getLocationList(int deviceId) throws Exception;

    List<Location> getLocationListByDate(int deviceId, String date) throws Exception;
}
