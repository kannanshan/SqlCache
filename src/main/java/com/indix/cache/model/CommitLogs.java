package com.indix.cache.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="COMMIT_LOGS")
public class CommitLogs implements Serializable {
	private static final long serialVersionUID = 1L;
}
