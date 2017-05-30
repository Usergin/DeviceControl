package com.shiz.repository.db.dao;

import com.shiz.model.data.event.Location;
import com.shiz.model.data.event.NetworkEvent;

import java.util.List;

/**
 * Created by OldMan on 18.05.2017.
 */
public interface NetworkStatusDao {
    void addNetworkEventList(int deviceId, List<NetworkEvent> locationList) throws Exception;
    List<NetworkEvent> getNetworkEventList(int deviceId) throws Exception;
}

