package com.shiz.repository.db.dao;

import com.shiz.entity.MessageEntity;
import com.shiz.model.data.event.Message;

import java.util.List;

/**
 * Created by OldMan on 10.05.2017.
 */
public interface MessageDao {
    void addMessageList(int deviceId, List<Message> messageEntities) throws Exception;
    List<Message> getMessageEntityList(int deviceId) throws Exception;

}
