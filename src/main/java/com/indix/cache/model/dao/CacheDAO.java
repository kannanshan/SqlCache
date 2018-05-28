/**
 * @packageName : com.indix.cache.model.dao
 * @className : CacheDAO.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.model.dao;

public interface CacheDAO {
	
	public String getKey(String key);
	public String setKey(String key,String value);

}
