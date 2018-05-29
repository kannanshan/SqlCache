package com.indix.cache.model.impl;

public class ImplBuilder {
	
	public static CacheDAOImpl getCacheImpleObject()
	{
		return new CacheDAOImpl();
	}
	
	public static CommitLogsDAOImpl getCommitLogsObject()
	{
		return new CommitLogsDAOImpl();
	}
	
	public static ClusterConfigDAOImpl getClusterConfigDAOImpl()
	{
		return new ClusterConfigDAOImpl();
	}


}
