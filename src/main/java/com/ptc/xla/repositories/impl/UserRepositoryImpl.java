package com.ptc.xla.repositories.impl;

import com.ptc.xla.models.User;
import com.ptc.xla.repositories.UserRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ssinghal
 * Created on 28-Jan-2016
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User get(String username) {
        return new User("123", "Shantanu");
    }

    @Override
    public boolean put(User user) {
        return false;
    }
}
