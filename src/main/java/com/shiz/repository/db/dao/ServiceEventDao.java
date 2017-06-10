package com.shiz.repository.db.dao;

import com.shiz.model.data.event.ServiceEvent;

import java.util.List;

/**
 * Created by OldMan on 19.05.2017.
 */
public interface ServiceEventDao {
    void addServiceEventList(int deviceId, List<ServiceEvent> serviceEventList) throws Exception;

    List<ServiceEvent> getServiceEventList(int deviceId) throws Exception;
}
