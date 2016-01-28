package com.ptc.xla.services;

import com.ptc.xla.models.User;

/**
 * Created by ssinghal
 * Created on 28-Jan-2016
 * If you refactor this code, remember: Code so clean you could eat off it!
 */
public interface UserService {

    User getUser(String username);

    boolean createUser();

}
