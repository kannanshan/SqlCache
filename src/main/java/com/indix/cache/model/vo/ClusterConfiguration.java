package com.indix.cache.model.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLUSTER_CONFIGURATION")
public class ClusterConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="CLUSTER_CONF_ID")
	private Integer clusterConfId;
	
	@Column(name = "IP")
	private String ip;

	@Column(name = "PORT")
	private Integer port;

	@Column(name = "COMMIT_LOG_ID")
	private Integer commitLogId;
	
	@Column(name = "CREATED_AT")
	private Timestamp createdAt;

	@Column(name = "UPDATED_AT")
	private Timestamp updatedAt;
}
