package com.terzetto.clientrpc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;

public class RPCClient {
	private Config conf = null;

	// Logger log = com.terzetto.clientrpc.Logging.getLoger(RPCClient.class);

	public RPCClient(String ip, String port) {
		conf = Config.getConfig(ip, port);
	}

	public RPCClient(Config conf) {
		this.conf = conf;
	}

	@SuppressWarnings("unused")
	public Object call(String mo, Object... args) throws Exception {
		Response res = null;
		String url = createJSON(mo, args);
		// System.out.println(request(mo, args));
		HttpClient httpClient = new HttpClient();
		httpClient.setFollowRedirects(false);
		httpClient.start();
		try {
			ContentResponse resp = httpClient.POST(conf.getURL())
					.param("call", request(mo, args)).send();
			httpClient.stop();
		//	 System.out.println(resp.getContentAsString());
			res = Response.convert(resp.getContentAsString());
		} catch (java.util.concurrent.TimeoutException e) {
			httpClient.stop();
			return ("Call to server method is success.\nServer is not responding.");
		} catch (Exception e) {
			httpClient.stop();
			e.printStackTrace();
			return ("Error: " + e.getMessage());
		}

		if (res.getCode() == 200) {
			return res.getResult();
		}
		if (res.getCode() == 500) {
			throw new Exception(res.getMessage().toString());
		}
		if (res.getCode() == 501) {
			throw new Exception(res.getMessage().toString());
		}
		if (res.getCode() == 502) {
			throw new Exception(res.getMessage().toString());
		}
		if (res.getCode() == 503) {
			throw new Exception(res.getMessage().toString());
		}
		if (res.getCode() == 504) {
			throw new Exception(res.getMessage().toString());
		}
		if (res.getCode() == 505) {
			throw new Exception(res.getMessage().toString());
		}
		return res;
	}

	@SuppressWarnings("unused")
	public Object call(String mo) throws Exception {
		Response res = null;
		String url = createJSON(mo, null);
		// System.out.println(request(mo, null));
		HttpClient httpClient = new HttpClient();
		httpClient.setFollowRedirects(false);
		httpClient.start();
		try {
			ContentResponse resp = httpClient.POST(conf.getURL())
					.param("call", request(mo, null)).send();
			httpClient.stop();
			// System.out.println(resp.getContentAsString());
			res = Response.convert(resp.getContentAsString());
		} catch (java.util.concurrent.TimeoutException e) {
			httpClient.stop();
			return ("Connection time out.");
		} catch (Exception e) {
			httpClient.stop();
			e.printStackTrace();
			return ("Error: " + e.getMessage());
		}

		if (res.getCode() == 200) {
			return res.getResult();
		}
		if (res.getCode() == 500) {
			throw new Exception(res.getMessage().toString());
		}
		if (res.getCode() == 501) {
			throw new Exception(res.getMessage().toString());
		}
		if (res.getCode() == 502) {
			throw new Exception(res.getMessage().toString());
		}
		if (res.getCode() == 503) {
			throw new Exception(res.getMessage().toString());
		}
		if (res.getCode() == 504) {
			throw new Exception(res.getMessage().toString());
		}
		if (res.getCode() == 505) {
			throw new Exception(res.getMessage().toString());
		}
		return res;
	}

	private String createJSON(String mo, Object[] args) {
		String url = conf.getURL();
		int i = 0;
		try {
			String[] methodObject = mo.split("\\.");
			url += "?method=" + methodObject[1];
			url += "&object=" + methodObject[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (args != null) {
			for (Object arg : args) {
				url += "&p" + i++ + "=" + arg;
			}
		}
		return url;
	}

	@SuppressWarnings("unused")
	private String request(String mo, Object[] args) {
		String data = null;
		try {
			String[] methodObject = mo.split("\\.");
			Request req = null;
			if (args == null) {
				req = new Request(methodObject[1], methodObject[0],
						new Object[0]);
			} else {
				req = new Request(methodObject[1], methodObject[0], args);
			}
			data = convert(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static String convert(Request res) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			Object jsonObject = mapper.readValue(
					mapper.writeValueAsString(res), Object.class);
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
					jsonObject);
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
