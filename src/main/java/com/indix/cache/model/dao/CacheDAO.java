/**
 * @packageName : com.indix.cache.model.dao
 * @className : CacheDAO.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.model.dao;

import java.util.Map;

public interface CacheDAO {
	
	public String getKey(String key);
	public void setKey(Map<String,String> value);
	public void updateKey(Map<String,Map<String,String>> value);	
}
