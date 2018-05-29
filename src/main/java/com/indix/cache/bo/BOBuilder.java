package com.indix.cache.bo;

public class BOBuilder {

	public static CacheBO getCacheBO()
	{
		return new CacheBO();
	}
	
	public static CommitLogsBO getCommitLogsBO()
	{
		return new CommitLogsBO();
	}
}
