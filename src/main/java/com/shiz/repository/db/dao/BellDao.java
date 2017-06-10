package com.shiz.repository.db.dao;

import com.shiz.model.data.event.Call;

import java.util.List;

/**
 * Created by OldMan on 09.05.2017.
 */
public interface BellDao {
    void addCallList(int deviceId, List<Call> appEntities) throws Exception;

    List<Call> getCallList(int deviceId) throws Exception;
}
