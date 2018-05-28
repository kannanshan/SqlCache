/**
 * @packageName : com.indix.cache.model.impl
 * @className : CacheImpl.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.model.impl;

import java.sql.Timestamp;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.indix.cache.common.HibernateUtil;
import com.indix.cache.model.dao.CacheDAO;
import com.indix.cache.model.vo.Cache;

public class CacheDAOImpl implements CacheDAO {

	@Override
	public String getKey(String key) {
		String value = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction txn = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			Cache cache = (Cache) session.get(Cache.class, key);
			if (cache != null) {
				value = cache.getValue();
			}
			txn.commit();

		} finally {
			session.close();
		}
		return value;
	}

	@Override
	public void setKey(Map<String, String> value) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction txn = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			for (String key : value.keySet()) {
				Cache cache = new Cache();
				cache.setKey(key);
				cache.setValue(value.get(key));
				cache.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				cache.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				session.saveOrUpdate(cache);
			}
			txn.commit();
		} finally {
			session.close();
		}
	}

}
