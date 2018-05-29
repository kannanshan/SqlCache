/**
 * @packageName : com.indix.cache.bo
 * @className : CacheBO.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.bo;

import java.util.Map;

import com.indix.cache.model.impl.ImplBuilder;

public class CacheBO {

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		String value = ImplBuilder.getCacheImpleObject().getKey(key);
		return value;
	}

	/**
	 * 
	 * @param valueObject
	 */
	public void setValue(Map<String, String> valueObject) {
		ImplBuilder.getCacheImpleObject().setKey(valueObject);
	}

	/**
	 * 
	 * @param valueObject
	 */
	public void putValue(Map<String, String> valueObject) {
		setValue(valueObject);
		CommitLogsBO.setFlag(true);
		addCommitLogs(valueObject);
	}

	/**
	 * 
	 * @param valueObject
	 */
	public void sinkData(Map<String, String> valueObject) {
		setValue(valueObject);
	}

	/**
	 * 
	 * @param valueObject
	 */
	public void addCommitLogs(Map<String, String> valueObject) {
		ImplBuilder.getCommitLogsObject().addCommitLogs(valueObject);
	}

	/**
	 * 
	 * @param valueObject
	 */
	public void addClusterConf(Map<String, String> valueObject) {
		ImplBuilder.getClusterConfigDAOImpl().addClusteConfigData(valueObject.get("ip"),
				Integer.parseInt(valueObject.get("port")));
	}

}
