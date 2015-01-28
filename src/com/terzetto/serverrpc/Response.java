package com.terzetto.serverrpc;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Response {
	private Object result = "";
	private String message = "";
	private int code = 200;
	public Response(){}
	public Response(int code, String message, Object result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public Object getResult() {
		return result;
	}

	public Object getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static String convert(Response res) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			Object jsonObject = mapper.readValue(mapper.writeValueAsString(res), Object.class);			
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}