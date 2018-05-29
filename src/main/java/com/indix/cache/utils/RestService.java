/**
 * @packageName : com.indix.cache.bo
 * @className : RestService.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.utils;

import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RestService {

	static HttpClient httpClient = HttpClientBuilder.create().build();

	public static Object sendPostRequest(String url, JSONObject postData)
			throws Exception {
		Object response = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json");
		StringEntity params = new StringEntity(postData.toString(), "UTF-8");
		httpPost.setEntity(params);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		return response;
	}

}
