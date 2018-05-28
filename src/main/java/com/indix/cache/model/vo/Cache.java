package com.indix.cache.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CACHE")
@NamedQuery(name="Cache.findAll", query="SELECT a FROM Cache a")
public class Cache implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CACHE_KEY")
	private String key;

	@Column(name = "CACHE_VALUE")
	private String value;

	@Column(name = "CREATED_AT")
	private Timestamp createdAt;

	@Column(name = "UPDATED_AT")
	private Timestamp updatedAt;
}
