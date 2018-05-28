package com.indix.cache.resource;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/")
public class CacheRequestResource {

	@GET
	@Path("get/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		return Response.status(200).build();

	}

	@POST
	@Path("set/")
	public Response setMsg(Map<String, String> value) {
		return Response.status(200).build();
	}

}