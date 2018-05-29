package com.indix.cache.model.dao;

import java.util.List;
import java.util.Map;
import com.indix.cache.model.vo.CommitLogs;

public interface CommitLogsDAO {

	public abstract void addCommitLogs(Map<String, String> values);

	public abstract List<CommitLogs> getCommitLogs(Integer commitLogId);

}
