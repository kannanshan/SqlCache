package com.indix.cache.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.indix.cache.bo.BOBuilder;

@Path("/")
public class CacheRequestResource {

	@GET
	@Path("get/{param}")
	public Response getMsg(@PathParam("param") String key) {
		String value = BOBuilder.getCacheBO().getValue(key);
		Map response = new HashMap();
		response.put("value", value);
		response.put("Status", "Success");
		return Response.status(200).entity(response).build();

	}

	@POST
	@Path("set/")
	public Response setMsg(Map<String, String> value) {
		return Response.status(200).build();
	}

}