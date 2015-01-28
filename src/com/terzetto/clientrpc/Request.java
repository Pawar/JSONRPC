package com.terzetto.clientrpc;

public class Request {
	private String method;
	private String object;
	private Object params[];
	
	public Request(){}
	
	public Request(String method, String object, Object[] params){
		this.method = method;
		this.object = object;
		this.params = params;
	}
	
	public Request(String method, String object, Object[][] params){
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

}
