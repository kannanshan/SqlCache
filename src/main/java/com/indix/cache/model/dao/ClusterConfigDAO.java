/**
 * @packageName : com.indix.cache.model.dao
 * @className : ClusterConfigDAO.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.model.dao;

import java.util.List;

import com.indix.cache.model.vo.ClusterConfiguration;

public interface ClusterConfigDAO {
	
	public abstract void addClusteConfigData(String ip,Integer port);
	
	public abstract List<ClusterConfiguration> getClusterConfiguration();
	
	public abstract void updateClusterConfiguration(ClusterConfiguration clusterConfiguration);

}
