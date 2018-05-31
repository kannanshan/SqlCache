package com.indix.cache.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.indix.cache.bo.BOBuilder;

@Path("/")
public class CacheRequestResource {

	@GET
	@Path("get/{param}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getKey(@PathParam("param") String key) {
		String value = BOBuilder.getCacheBO().getValue(key);
		Map response = new HashMap();
		response.put("value", value);
		return Response.status(200).entity(response).build();

	}

	@POST
	@Path("set")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response setKey(Map<String, String> value) {
		BOBuilder.getCacheBO().putValue(value);
		return Response.status(200).build();
	}
	
	@POST
	@Path("sink")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response sinkdata(Map<String, Map<String,String>> value) {
		BOBuilder.getCacheBO().sinkData(value);
		return Response.status(200).build();
	}
	
	@POST
	@Path("nodes")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response discoveredNodes(Map<String, String> value) {
		BOBuilder.getCacheBO().addClusterConf(value);
		return Response.status(200).build();
	}

}