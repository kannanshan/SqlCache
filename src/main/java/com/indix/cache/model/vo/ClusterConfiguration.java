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

	/**
	 * @return the clusterConfId
	 */
	public Integer getClusterConfId() {
		return clusterConfId;
	}

	/**
	 * @param clusterConfId the clusterConfId to set
	 */
	public void setClusterConfId(Integer clusterConfId) {
		this.clusterConfId = clusterConfId;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the commitLogId
	 */
	public Integer getCommitLogId() {
		return commitLogId;
	}

	/**
	 * @param commitLogId the commitLogId to set
	 */
	public void setCommitLogId(Integer commitLogId) {
		this.commitLogId = commitLogId;
	}

	/**
	 * @return the createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
