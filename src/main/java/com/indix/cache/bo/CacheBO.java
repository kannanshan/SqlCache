/**
 * @packageName : com.indix.cache.bo
 * @className : CacheBO.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.bo;

import java.util.Map;

import com.indix.cache.model.impl.ImplBuilder;

public class CacheBO {

	public String getValue(String key) {
		String value = ImplBuilder.getCacheImpleObject().getKey(key);
		return value;
	}

	public void setValue(Map<String, String> valueObject) {
		for (String key : valueObject.keySet()) {
			ImplBuilder.getCacheImpleObject().setKey(key, valueObject.get(key));
		}
	}

}
