/**
 * @packageName : com.indix.cache.model.impl
 * @className : ClusterConfigDAOImpl.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.model.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.indix.cache.common.HibernateUtil;
import com.indix.cache.model.dao.ClusterConfigDAO;
import com.indix.cache.model.vo.Cache;
import com.indix.cache.model.vo.ClusterConfiguration;
import com.indix.cache.model.vo.CommitLogs;

public class ClusterConfigDAOImpl implements ClusterConfigDAO {

	@Override
	public void addClusteConfigData(String ip, Integer port) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction txn = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			ClusterConfiguration clusterConfiguration = new ClusterConfiguration();
			clusterConfiguration.setIp(ip);
			clusterConfiguration.setPort(port);
			clusterConfiguration.setCommitLogId(0);
			clusterConfiguration.setStatus("ACTIVE");
			clusterConfiguration.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			clusterConfiguration.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			session.saveOrUpdate(clusterConfiguration);
			txn.commit();
		} finally {
			session.close();
		}

	}

	@Override
	public List<ClusterConfiguration> getClusterConfiguration() {
		List<ClusterConfiguration> clusterList = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction txn = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			String hql = "SELECT CLUSTER_CONF_ID,IP,PORT,COMMIT_LOG_ID,STATUS,CREATED_AT,UPDATED_AT FROM CLUSTER_CONFIGURATION WHERE STATUS != \"INACTIVE\"";
			Query q = session.createSQLQuery(hql).addEntity(ClusterConfiguration.class);
			clusterList = (List<ClusterConfiguration>) q.list();
			txn.commit();
		} finally {
			session.close();
		}
		return clusterList;
	}

	@Override
	public void updateClusterConfiguration(ClusterConfiguration clusterConfiguration) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction txn = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			session.saveOrUpdate(clusterConfiguration);
			txn.commit();
		} finally {
			session.close();
		}

	}

}
