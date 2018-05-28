/**
 * @packageName : com.indix.cache.model.impl
 * @className : CacheImpl.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.model.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.indix.cache.common.HibernateUtil;
import com.indix.cache.model.dao.CacheDAO;
import com.indix.cache.model.vo.Cache;

public class CacheDAOImpl implements CacheDAO{

	@Override
	public String getKey(String key) {
		String value;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction txn = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			String hql = "SELECT CACHE_VALUE FROM CACHE WHERE CACHE_KEY=:CACHE_KEY";
			Query q = session.createSQLQuery(hql).addEntity(Cache.class);
			//Query q = session.createQuery(hql);
			q.setParameter("CACHE_KEY", key);
			Object obj = q.list();
			txn.commit();

		} finally {
			session.close();
		}
		return "s";
	}

	@Override
	public String setKey(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
