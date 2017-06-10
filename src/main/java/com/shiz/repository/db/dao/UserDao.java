package com.shiz.repository.db.dao;

import com.shiz.model.Authentication;
import com.shiz.model.User;

/**
 * Created by OldMan on 09.06.2017.
 */
public interface UserDao {
    void addUser(User user) throws Exception;

    User getUser(Authentication authentication) throws Exception;
}
