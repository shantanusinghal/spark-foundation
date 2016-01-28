package com.ptc.xla.repository;

import org.springframework.stereotype.Repository;

import com.ptc.xla.model.User;

@Repository
public class UserRepositoryHibernateImpl extends BaseHibernateDao implements UserRepository {

  @Override
  public String getUserName() {
    User user =  (User) getSession().createQuery("from " + User.class.getSimpleName()).list().get(0);
    return user.getUserName();
  }

}
