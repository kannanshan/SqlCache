/**
 * @packageName : com.indix.cache.model.impl
 * @className : CacheImpl.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.model.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.indix.cache.common.HibernateUtil;
import com.indix.cache.model.dao.CacheDAO;
import com.indix.cache.model.vo.Cache;
import com.indix.cache.model.vo.ClusterConfiguration;

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
	
	@Override
	public void updateKey(Map<String, Map<String,String>> value) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction txn = null;
		try {
			session = sessionFactory.openSession();
			//txn = session.beginTransaction();
			for (String key : value.keySet()) {
				session = sessionFactory.openSession();
				txn = session.beginTransaction();
				String hql = "insert into cache(cache_key,cache_value,created_at,updated_at) values (:KEY,:VALUE,Now(),:AT1) on duplicate key update cache_value=if(updated_at<values(updated_at),values(cache_value),cache_value),updated_at = IF(updated_at < VALUES(updated_at), VALUES(updated_at), updated_at)";
				Query q = session.createSQLQuery(hql);
				q.setParameter("KEY", key);
				q.setParameter("VALUE", value.get(key).get("value"));
				q.setParameter("AT1", value.get(key).get("timestamp"));
				q.executeUpdate();
				txn.commit();
				session.close();
			}
			//txn.commit();
		} finally {
			if(session.isOpen()){
			session.close();
			}
		}
	}
	

}
