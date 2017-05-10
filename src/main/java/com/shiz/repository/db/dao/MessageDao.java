package com.shiz.repository.db.dao;

import com.shiz.entity.MessageEntity;

import java.util.List;

/**
 * Created by OldMan on 10.05.2017.
 */
public interface MessageDao {
    void addMessageList(int deviceId, List<MessageEntity> messageEntities) throws Exception;
    List<MessageEntity> getMessageEntityList(int deviceId) throws Exception;

}
