package com.ptc.xla.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class BaseHibernateDao {

	private SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Used to batch save (or update) a group of persistable entities. Use this if
	 * you want to make this save an atomic update and have it done as speedily as
	 * possible.
	 *
	 * @param entityList
	 */
	protected void saveAll(List<?> entityList) {
		Session session = getSession();
		for (int i = 0; i < entityList.size(); i++) {
			Object entity = entityList.get(i);
			session.saveOrUpdate(entity);
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> find(final String queryString, final Object... values) throws DataAccessException {
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.list();
	}

}