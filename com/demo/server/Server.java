package com.demo.server;

import com.terzetto.serverrpc.*;

public class Server {
	public static void main(String[] args){
		RPCServer rpcserver = new RPCServer("localhost","3887");
		rpcserver.addClass(Demo.class, "demo1");
		rpcserver.addClass(Demo.class, "demo2");
		rpcserver.start();
	}
}
