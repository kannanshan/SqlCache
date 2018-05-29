import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * @packageName : 
 * @className : CacheRequestResourceTest.java
 * @date : 29-May-2018
 * @author : kannans
 * @version : 1.0
 */

public class CacheRequestResourceTest {
	
	private static Integer port = 8000;
	
	@BeforeClass
	public static void setup() throws Exception {

	}

	@AfterClass
	public static void end() throws Exception {

	}

	
	public Response postCall(String jsonBody, String url) {
		RestAssured.port =port;
		Response response = RestAssured.given()
				.contentType("application/json").body(jsonBody).when().post("/SqslCache/" + url)
				.then().extract().response();
		return response;
	}
	
	//@Test
	public void setCacheTest()
	{
		String json ="{\"cricket\": \"dhoni\"}";
		Response response = postCall(json, "set");
		response.then().statusCode(200);
	}
	
	//@Test
	public void getCacheTest()
	{
		String json ="{\"cricket\": \"dhoni\"}";
		Response response = postCall(json, "get");
		response.then().statusCode(200);
	}
	
	//@Test
	public void sinkCacheTest()
	{
		String json ="{\"cricket\": \"dhoni\"}";
		Response response = postCall(json, "sink");
		response.then().statusCode(200);
	}
	
	

}
