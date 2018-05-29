/**
 * @packageName : com.indix.cache.bo
 * @className : RestService.java
 * @date : 28-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.json.simple.JSONObject;

public class RestService {

	static HttpClient httpClient = null;

	static {
		ConnectionKeepAliveStrategy keepAliveStrategy = new ConnectionKeepAliveStrategy() {
			@Override
			public long getKeepAliveDuration(HttpResponse arg0, org.apache.http.protocol.HttpContext arg1) {
				return 30000;
			}
		};

		// Connection pool object
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(50);
		connManager.setDefaultMaxPerRoute(20);

		RequestConfig config = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();

		// Request retry object
		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
			@Override
			public boolean retryRequest(IOException exception, int executionCount,
					org.apache.http.protocol.HttpContext arg2) {
				if (executionCount > 3) {
					return false;
				}
				return true;
			}
		};

		httpClient = HttpClientBuilder.create().setKeepAliveStrategy(keepAliveStrategy)
				.setConnectionManager(connManager).setRetryHandler(httpRequestRetryHandler)
				.setDefaultRequestConfig(config).build();
	}

	public static Object sendPostRequest(String url, JSONObject postData) throws Exception {
		Object response = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json");
		StringEntity params = new StringEntity(postData.toString(), "UTF-8");
		httpPost.setEntity(params);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		return response;
	}

}
