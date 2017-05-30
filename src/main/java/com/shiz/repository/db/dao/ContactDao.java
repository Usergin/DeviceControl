package com.shiz.repository.db.dao;

import com.shiz.model.data.Contact;
import com.shiz.model.data.event.NetworkEvent;

import java.util.List;

/**
 * Created by OldMan on 23.05.2017.
 */
public interface ContactDao {
    void addContactList(int deviceId, List<Contact> contactList) throws Exception;
    List<Contact> getContactList(int deviceId) throws Exception;
}
