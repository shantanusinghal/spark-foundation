package com.ptc.xla.services.impl;

import com.ptc.xla.models.User;
import com.ptc.xla.repositories.UserRepository;
import com.ptc.xla.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ssinghal
 * Created on 28-Jan-2016
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(String username) {
        return userRepository.get("123");
    }

    @Override
    public boolean createUser() {
        return false;
    }

}
