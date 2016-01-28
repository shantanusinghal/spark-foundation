package com.ptc.xla.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptc.xla.repository.UserRepository;
import com.ptc.xla.services.LoginService;

/**
 * @author tbhasme
 */
@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  UserRepository userRepo;

  @Override
  public String getUserName() {
    return userRepo.getUserName();
  }

}
