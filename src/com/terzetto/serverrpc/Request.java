package com.terzetto.serverrpc;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Request {
	private String method;
	private String object;
	private Object params[];

	public Request() {
	}

	public Request(String method, String object, Object[] params) {
		this.method = method;
		this.object = object;
		this.params = params;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public static Request convert(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Request res = new Request();
		try {
			res = mapper.readValue(json, Request.class);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
}
