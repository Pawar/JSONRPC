package com.terzetto.clientrpc;

public class Config {
	private String serverIP = "";
	private String serverPort = "";
	private int timeout = 5;
	static Config config = null;
	

	private Config(String ip, String port) {
		this.serverIP = ip;
		this.serverPort = port;
	}

	public static Config getConfig(Configuration conf) {
		if (config == null) {
			return new Config(conf.getServerIP(), conf.getServerPort());
		} else
			return config;
	}

	public static Config getConfig(String ip, String port) {
		if (config == null) {
			return new Config(ip, port);
		} else
			return config;
	}
	
	public static Config getConfig() {
		return config;
	}

	public String getServerIP() {
		return this.serverIP;
	}

	public int getServerPort() {
		return Integer.parseInt(this.serverPort);
	}
	
	public String getURL(){
		return "http://"+this.serverIP+":"+this.serverPort+"/RPC";
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
