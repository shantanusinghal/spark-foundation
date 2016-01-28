package com.ptc.xla.repositories;

import com.ptc.xla.models.User;

/**
 * Created by ssinghal
 * Created on 28-Jan-2016
 */
public interface UserRepository {

    User get(String username);

    boolean put(User user);

}
