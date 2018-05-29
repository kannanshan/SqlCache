/**
 * @packageName : com.indix.cache.common
 * @className : AppLoader.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.indix.cache.bo.CommitLogsBO;
import com.indix.cache.bo.CommitLogsBO.CommitLogWorker;

public class AppLoader implements ServletContextListener {

		@Override
		public void contextInitialized(ServletContextEvent servletContextEvent) {
			BackGroundWorker.initiateWorker(5000, new CommitLogWorker());
		}

		@Override
		public void contextDestroyed(ServletContextEvent servletContextEvent) {
			BackGroundWorker.stopWorkers();
		}

	}
