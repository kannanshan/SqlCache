/**
 * @packageName : com.indix.cache.common
 * @className : AppLoader.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.common;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.indix.cache.bo.CommitLogsBO;
import com.indix.cache.bo.CommitLogsBO.CommitLogWorker;
import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.PooledDataSource;

public class AppLoader implements ServletContextListener {

		@Override
		public void contextInitialized(ServletContextEvent servletContextEvent) {
			BackGroundWorker.initiateWorker(5000, new CommitLogWorker());
		}

		@Override
		public void contextDestroyed(ServletContextEvent servletContextEvent) {
			BackGroundWorker.stopWorkers();
			deRegisterDrivers();
			closePooledConnections();
		}
		

		/**
		 *
		 */
		private void deRegisterDrivers() {
			Enumeration<Driver> drivers = DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				Driver driver = drivers.nextElement();
				try {
					DriverManager.deregisterDriver(driver);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 *
		 */
		private void closePooledConnections() {
			Iterator<Set> it = C3P0Registry.getPooledDataSources().iterator();
			while (it.hasNext()) {
				try {
					PooledDataSource dataSource = (PooledDataSource) it.next();
					dataSource.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
