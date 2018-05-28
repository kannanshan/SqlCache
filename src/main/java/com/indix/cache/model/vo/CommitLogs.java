package com.indix.cache.model.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "COMMIT_LOGS")
public class CommitLogs implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="COMMIT_LOG_ID")
	private Integer commitLogId;
	
	@Column(name = "CACHE_KEY")
	private String key;

	@Column(name = "CACHE_VALUE")
	private String value;
	
	@Column(name = "OPERATION")
	private String operation;

	@Column(name = "CREATED_AT")
	private Timestamp createdAt;

	@Column(name = "UPDATED_AT")
	private Timestamp updatedAt;
}
