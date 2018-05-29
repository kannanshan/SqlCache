/**
 * @packageName : com.indix.cache.bo
 * @className : CommitLogsBO.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.bo;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.json.simple.JSONObject;

import com.indix.cache.common.BackGroundWorker;
import com.indix.cache.model.impl.ImplBuilder;
import com.indix.cache.model.vo.ClusterConfiguration;
import com.indix.cache.model.vo.CommitLogs;
import com.indix.cache.utils.RestService;

public class CommitLogsBO {

	private static AtomicBoolean flag = new AtomicBoolean(true);;

	public static void initiateCommitLogsWorkers() {
		BackGroundWorker.initiateWorker(100, new CommitLogWorker());
	}

	public List<ClusterConfiguration> getClusterConfiguration() {
		List<ClusterConfiguration> configList = null;
		return configList;
	}

	public static class CommitLogWorker implements Runnable {

		@Override
		public void run() {
			if (flag.get()) {
				List<ClusterConfiguration> clusterConfList = ImplBuilder.getClusterConfigDAOImpl()
						.getClusterConfiguration();
				for (ClusterConfiguration clusterConf : clusterConfList) {
					List<CommitLogs> commitLogList = ImplBuilder.getCommitLogsObject()
							.getCommitLogs(clusterConf.getCommitLogId());
					CommitLogsBO.setFlag(false);
					if (commitLogList.size() > 0) {
						BackGroundWorker.submitWork(new SinkDataWorker(commitLogList, clusterConf));
					}
				}
			}
		}
	}

	public static class SinkDataWorker implements Runnable {

		private List<CommitLogs> commitLogs;
		private ClusterConfiguration clusterConf;

		public SinkDataWorker(List<CommitLogs> commitLogs, ClusterConfiguration clusterConf) {
			this.commitLogs = commitLogs;
			this.clusterConf = clusterConf;
		}

		@Override
		public void run() {
			try {
				JSONObject valueMap = null;
				Integer commitLogId = 0;
				for (CommitLogs commitLog : commitLogs) {
					valueMap = new JSONObject();
					valueMap.put(commitLog.getKey(), commitLog.getValue());
					commitLogId = commitLog.getCommitLogId();
				}
				RestService.sendPostRequest(
						"http://" + clusterConf.getIp() + ":" + clusterConf.getPort() + "/SqlCache/sink/", valueMap);
				clusterConf.setCommitLogId(commitLogId);
				clusterConf.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				ImplBuilder.getClusterConfigDAOImpl().updateClusterConfiguration(clusterConf);
			} catch (Exception e) {
				String status = clusterConf.getStatus();
				Timestamp updatedAt = clusterConf.getUpdatedAt();
				if (status.equalsIgnoreCase("ACTIVE")) {
					System.out.println(" marked Failed");
					clusterConf.setStatus("FAILED");
				} else {
					Timestamp beforeTime = new Timestamp(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(30));
					if (updatedAt.before(beforeTime)) {
						System.out.println(" marked inactive");
						clusterConf.setStatus("INACTIVE");
					}
					else
					{
						System.out.println(" in Failed");
					}
				}
				ImplBuilder.getClusterConfigDAOImpl().updateClusterConfiguration(clusterConf);
				CommitLogsBO.setFlag(true);
				System.out.println("Error in sinking data");
			}
		}

	}

	/**
	 * @return the flag
	 */
	public static AtomicBoolean getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public static void setFlag(Boolean flag) {
		CommitLogsBO.flag.set(flag);
	}

}
