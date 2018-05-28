package com.indix.cache.model.impl;

import java.sql.Timestamp;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.indix.cache.common.HibernateUtil;
import com.indix.cache.model.dao.CommitLogsDAO;
import com.indix.cache.model.vo.Cache;
import com.indix.cache.model.vo.CommitLogs;

public class CommitLogsDAOImpl implements CommitLogsDAO {

	@Override
	public void addCommitLogs(Map<String, String> values) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction txn = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			for (String key : values.keySet()) {
				CommitLogs commitLogs = new CommitLogs();
				commitLogs.setOperation("set");
				commitLogs.setKey(key);
				commitLogs.setValue(values.get(key));
				commitLogs.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				commitLogs.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				session.saveOrUpdate(commitLogs);
			}
			txn.commit();
		} finally {
			session.close();
		}

	}

}
